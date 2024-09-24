package up.pdp.apprecipes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import up.pdp.apprecipes.utils.annotations.Email;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignInDto implements Serializable {
    @Email
    private String email;
    private String password;
}
