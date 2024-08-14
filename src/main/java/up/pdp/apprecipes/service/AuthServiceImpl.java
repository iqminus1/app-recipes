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

    @Cacheable(value = "user", key = "#username")
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByUsername(username);
    }

    @Override
    public ApiResultDTO<?> signIn(SignInDTO signIn) {
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    signIn.getUsername(),
                    signIn.getPassword()
            );
            authenticationProvider.authenticate(authentication);
            String token = jwtProvider.generateToken(signIn.getUsername());
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
                signUp.getUsername(),
                passwordEncoder.encode(signUp.getPassword()),
                signUp.getEmail(),
                false,
                false
        );
        userRepository.save(user);
        mailService.sendVerify(signUp.getEmail(), signUp.getUsername());
        return ApiResultDTO.success("Verify email");
    }

    @Override
    public ApiResultDTO<?> verifyEmail(String username, String code) {
        User user = userRepository.getByUsername(username);

        Code codeEntity = codeRepository.getByEmail(user.getEmail());

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
