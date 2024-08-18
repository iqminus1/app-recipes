package up.pdp.apprecipes.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.pdp.apprecipes.dto.SignInDto;
import up.pdp.apprecipes.dto.SignUpDto;
import up.pdp.apprecipes.dto.UserCRUDDto;
import up.pdp.apprecipes.service.AuthService;
import up.pdp.apprecipes.utils.AppConst;

@RequestMapping(AppConst.API_V1 + "/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpDto signUpDTO) {
        return ResponseEntity.status(200).body(authService.signUp(signUpDTO));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInDto signIn) {
        return ResponseEntity.status(200).body(authService.signIn(signIn));
    }

    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam String email, @RequestParam String code) {
        return ResponseEntity.status(200).body(authService.verifyEmail(email, code));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(UserCRUDDto crudDto) {
        return ResponseEntity.ok(authService.update(crudDto));
    }
}
