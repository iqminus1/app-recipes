package up.pdp.apprecipes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VerifyEmailDto {
    private String email;
    private Integer code;
}
