package up.pdp.apprecipes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpDto {
    private String email;
    private String name;
    private String password;
    private String confirmPassword;
}
