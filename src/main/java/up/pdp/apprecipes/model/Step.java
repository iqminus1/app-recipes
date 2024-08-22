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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@SQLRestriction("deleted = false")
@SQLDelete(sql = "update step set deleted = true where id = ?")
public class Step extends AbsUUIDEntity {
    private Integer step;
    private String description;
}
