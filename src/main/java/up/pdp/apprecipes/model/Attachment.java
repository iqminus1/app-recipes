package up.pdp.apprecipes.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import up.pdp.apprecipes.model.templates.AbsUUIDEntity;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@SQLRestriction("deleted = false")
@SQLDelete(sql="update attachment set deleted = true where id = ?")
public class Attachment extends AbsUUIDEntity implements Serializable {
    private String originalName;
    private String name;
    private String type;
    private Long size;
    private String path;
}

