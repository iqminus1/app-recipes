package up.pdp.apprecipes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import up.pdp.apprecipes.model.templates.AbsUUIDEntity;

import java.util.UUID;

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
    @OneToOne
    private Attachment attachment;
}
