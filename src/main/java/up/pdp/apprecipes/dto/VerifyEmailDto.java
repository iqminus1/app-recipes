package up.pdp.apprecipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VerifyEmailDto {
    private String email;
    private String code;
}
