package up.pdp.apprecipes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCRUDDto {
    private UUID attachmentId;
    private String name;
    private String email;
    private String password;
    private String location;
}
