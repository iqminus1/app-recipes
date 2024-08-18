package up.pdp.apprecipes.dto;

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
    private String acceptedPassword;
}
