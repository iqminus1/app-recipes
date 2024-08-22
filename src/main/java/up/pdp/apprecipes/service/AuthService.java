package up.pdp.apprecipes.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import up.pdp.apprecipes.dto.response.ApiResultDto;
import up.pdp.apprecipes.dto.request.SignInDto;
import up.pdp.apprecipes.dto.request.SignUpDto;
import up.pdp.apprecipes.dto.request.UserCRUDDto;

public interface AuthService extends UserDetailsService {
    ApiResultDto<?> signIn(SignInDto signUp);

    ApiResultDto<?> signUp(SignUpDto signIn);

    ApiResultDto<?> verifyEmail(String email, String code);

    ApiResultDto<?> update(UserCRUDDto crudDto);
}
