package up.pdp.apprecipes.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.pdp.apprecipes.dto.SignInDTO;
import up.pdp.apprecipes.dto.SignUpDTO;
import up.pdp.apprecipes.service.AuthService;
import up.pdp.apprecipes.utils.AppConst;

@RequestMapping(AppConst.API_V1 + "/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpDTO signUpDTO) {
        return ResponseEntity.status(200).body(authService.signUp(signUpDTO));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInDTO signIn) {
        return ResponseEntity.status(200).body(authService.signIn(signIn));
    }

    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam String email, @RequestParam String code) {
        return ResponseEntity.status(200).body(authService.verifyEmail(email, code));
    }
}
