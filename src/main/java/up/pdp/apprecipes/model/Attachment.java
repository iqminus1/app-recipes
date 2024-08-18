package up.pdp.apprecipes.model;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import up.pdp.apprecipes.model.templates.AbsUUIDEntity;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@SQLRestriction("deleted = false")
@SQLDelete(sql="update attachment set deleted = true where id = ?")
public class Attachment extends AbsUUIDEntity implements Serializable {
    private String name;
    private String originalName;
    private String path;
    private String contentType;
    private Long size;
}
