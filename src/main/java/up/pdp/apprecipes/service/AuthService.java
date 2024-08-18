package up.pdp.apprecipes.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import up.pdp.apprecipes.dto.ApiResultDto;
import up.pdp.apprecipes.dto.SignInDto;
import up.pdp.apprecipes.dto.SignUpDto;

public interface AuthService extends UserDetailsService {
    ApiResultDto<?> signIn(SignInDto signUp);

    ApiResultDto<?> signUp(SignUpDto signIn);

    ApiResultDto<?> verifyEmail(String email, String code);
}
