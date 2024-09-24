package up.pdp.apprecipes.service;

import up.pdp.apprecipes.dto.request.UserDeleteDto;
import up.pdp.apprecipes.dto.request.SignInDto;
import up.pdp.apprecipes.dto.request.SignUpDto;
import up.pdp.apprecipes.dto.request.UserCRUDDto;
import up.pdp.apprecipes.dto.response.JwtTokenDto;
import up.pdp.apprecipes.dto.response.SuccessResponse;
import up.pdp.apprecipes.dto.response.UserDto;

public interface UserService {
    SuccessResponse signUp(SignUpDto signIn);
    JwtTokenDto verifyEmail(String email, Integer code);
    JwtTokenDto signIn(SignInDto signUp);
    UserDto getById(String id);
    UserDto update(UserCRUDDto crudDto);
    SuccessResponse delete(UserDeleteDto deleteDto);
}
