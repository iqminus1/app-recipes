package up.pdp.apprecipes.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import up.pdp.apprecipes.dto.ApiResultDTO;
import up.pdp.apprecipes.dto.SignInDTO;
import up.pdp.apprecipes.dto.SignUpDTO;
import up.pdp.apprecipes.dto.TokenDTO;
import up.pdp.apprecipes.model.Code;
import up.pdp.apprecipes.model.User;
import up.pdp.apprecipes.repository.CodeRepository;
import up.pdp.apprecipes.repository.UserRepository;
import up.pdp.apprecipes.security.JwtProvider;

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

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.getByEmail(email);
    }

    @Override
    public ApiResultDTO<?> signIn(SignInDTO signIn) {
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    signIn.getEmail(),
                    signIn.getPassword()
            );
            authenticationProvider.authenticate(authentication);
            String token = jwtProvider.generateToken(signIn.getEmail());
            return ApiResultDTO.success(new TokenDTO(token));
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public ApiResultDTO<?> signUp(SignUpDTO signUp) {
        if (!Objects.equals(signUp.getPassword(), signUp.getAcceptedPassword())) {
            throw new BadRequestException();
        }
        User user = new User(
                signUp.getEmail(),
                passwordEncoder.encode(signUp.getPassword()),
                signUp.getName(),
                false,
                false
        );
        userRepository.save(user);
        mailService.sendVerify(signUp.getEmail());
        return ApiResultDTO.success("Verify email");
    }

    @Override
    public ApiResultDTO<?> verifyEmail(String email, String code) {
        User user = userRepository.getByEmail(email);

        Code codeEntity = codeRepository.getByEmail(email);

        if (!codeEntity.getCodeString().equals(code)) {
            if (codeEntity.getAttempt() == 1) {
                codeRepository.delete(codeEntity);
                userRepository.delete(user);
                return ApiResultDTO.error("Your attempt ended");
            }
            codeEntity.setAttempt(codeEntity.getAttempt() - 1);
            codeRepository.save(codeEntity);
            return ApiResultDTO.error("Code not equals");
        }

        codeRepository.delete(codeEntity);

        user.setEnable(true);
        userRepository.save(user);

        return ApiResultDTO.success("Ok");
    }

}
