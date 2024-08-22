package up.pdp.apprecipes.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.request.SignInDto;
import up.pdp.apprecipes.dto.request.SignUpDto;
import up.pdp.apprecipes.dto.request.UserCRUDDto;
import up.pdp.apprecipes.dto.response.ApiResultDto;
import up.pdp.apprecipes.dto.response.TokenDto;
import up.pdp.apprecipes.model.Code;
import up.pdp.apprecipes.model.User;
import up.pdp.apprecipes.repository.AttachmentRepository;
import up.pdp.apprecipes.repository.CodeRepository;
import up.pdp.apprecipes.repository.UserRepository;
import up.pdp.apprecipes.security.JwtProvider;
import up.pdp.apprecipes.service.AuthService;
import up.pdp.apprecipes.service.MailService;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final CodeRepository codeRepository;
    private final AuthenticationProvider authenticationProvider;
    private final JwtProvider jwtProvider;
    private final AttachmentRepository attachmentRepository;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.getByEmail(email);
    }

    @Override
    public ApiResultDto<?> signIn(SignInDto signIn) {
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    signIn.getEmail(),
                    signIn.getPassword()
            );
            authenticationProvider.authenticate(authentication);
            String token = jwtProvider.generateToken(signIn.getEmail());
            return ApiResultDto.success(new TokenDto(token));
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public ApiResultDto<?> signUp(SignUpDto signUp) {
        if (!Objects.equals(signUp.getPassword(), signUp.getAcceptedPassword())) {
            throw new BadRequestException();
        }
        User user = new User(
                signUp.getEmail(),
                passwordEncoder.encode(signUp.getPassword()),
                signUp.getName(),
                null,
                null,
                false,
                false
        );
        userRepository.save(user);
        mailService.sendVerify(signUp.getEmail());
        return ApiResultDto.success("Verify email");
    }

    @Override
    public ApiResultDto<?> verifyEmail(String email, String code) {
        User user = userRepository.getByEmail(email);

        Code codeEntity = codeRepository.getByEmail(email);

        if (!codeEntity.getCodeString().equals(code)) {
            if (codeEntity.getAttempt() == 1) {
                codeRepository.delete(codeEntity);
                userRepository.delete(user);
                return ApiResultDto.error("Your attempt ended");
            }
            codeEntity.setAttempt(codeEntity.getAttempt() - 1);
            codeRepository.save(codeEntity);
            return ApiResultDto.error("Code not equals");
        }

        codeRepository.delete(codeEntity);

        user.setEnable(true);
        userRepository.save(user);

        return ApiResultDto.success("Ok");
    }

    @Override
    public ApiResultDto<?> update(UserCRUDDto crudDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (crudDto.getAttachmentId() != null)
            user.setAttachment(attachmentRepository.getById(crudDto.getAttachmentId()));
        if (crudDto.getEmail() != null)
            user.setEmail(crudDto.getEmail());
        if (crudDto.getPassword() != null)
            user.setPassword(passwordEncoder.encode(crudDto.getPassword()));
        if (crudDto.getName() != null)
            user.setName(crudDto.getName());
        if (crudDto.getLocation() != null)
            user.setLocation(crudDto.getLocation());

        userRepository.save(user);
        return ApiResultDto.success("Ok");
    }

}
