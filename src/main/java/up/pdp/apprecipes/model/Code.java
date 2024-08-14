package up.pdp.apprecipes.model;

import jakarta.persistence.Entity;
import lombok.*;
import up.pdp.apprecipes.model.templates.AbsUUIDEntity;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Code extends AbsUUIDEntity implements Serializable {
    private String email;
    private String codeString;
    private Integer attempt;
}
