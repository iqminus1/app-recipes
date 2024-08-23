package up.pdp.apprecipes.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.pdp.apprecipes.dto.request.*;
import up.pdp.apprecipes.service.UserService;
import up.pdp.apprecipes.utils.AppConst;

@RequestMapping(AppConst.API_V1 + "/auth")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService authService;

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpDto signUpDTO) {
        return ResponseEntity.status(200).body(authService.signUp(signUpDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInDto signIn) {
        return ResponseEntity.status(200).body(authService.signIn(signIn));
    }

    @PostMapping("/verify/email")
    public ResponseEntity<?> verifyEmail(@RequestBody VerifyEmailDto dto) {
        return ResponseEntity.status(200).body(authService.verifyEmail(dto.getEmail(),dto.getCode()));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(UserCRUDDto crudDto) {
        return ResponseEntity.ok(authService.update(crudDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody UserDeleteDto crudDto) {
        return ResponseEntity.ok(authService.delete(crudDto));
    }
}
