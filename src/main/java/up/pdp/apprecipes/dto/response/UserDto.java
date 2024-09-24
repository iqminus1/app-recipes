package up.pdp.apprecipes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.model.User;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class UserDto {
    private String email;
    private String password;
    private String name;
    private UUID attachmentId;
    private String location;
    private boolean enable;
    private boolean admin;

    public UserDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.attachmentId = user.getAttachment().getId();
        this.location = user.getLocation();
        this.enable = user.isEnable();
        this.admin = user.isAdmin();
    }
}
