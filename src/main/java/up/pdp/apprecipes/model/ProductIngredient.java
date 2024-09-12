package up.pdp.apprecipes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
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
@SQLDelete(sql = "update product_ingredient set deleted = true where id = ?")
public class ProductIngredient extends AbsUUIDEntity {
    @ManyToOne
    private Product product;
    @ManyToOne
    private Ingredient ingredient;
    private Long quantity;
}
