package up.pdp.apprecipes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import up.pdp.apprecipes.model.templates.AbsUUIDEntity;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "ingredient")
@SQLRestriction("deleted = false")
@SQLDelete(sql = "update ingredient set deleted = true where id = ?")
public class Ingredient extends AbsUUIDEntity {
    private String name;
    @ManyToOne
    private Attachment attachment;
}
