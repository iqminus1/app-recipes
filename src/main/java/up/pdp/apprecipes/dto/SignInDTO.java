package up.pdp.apprecipes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignInDTO implements Serializable {
    private String email;
    private String password;
}
