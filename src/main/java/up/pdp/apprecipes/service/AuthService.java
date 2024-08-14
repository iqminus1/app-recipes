package up.pdp.apprecipes.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import up.pdp.apprecipes.dto.ApiResultDTO;
import up.pdp.apprecipes.dto.SignInDTO;
import up.pdp.apprecipes.dto.SignUpDTO;

public interface AuthService extends UserDetailsService {
    ApiResultDTO<?> signIn(SignInDTO signUp);

    ApiResultDTO<?> signUp(SignUpDTO signIn);

    ApiResultDTO<?> verifyEmail(String username, String code);
}
