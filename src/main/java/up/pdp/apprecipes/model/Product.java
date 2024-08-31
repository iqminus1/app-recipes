package up.pdp.apprecipes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import up.pdp.apprecipes.model.templates.AbsUUIDEntity;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@SQLRestriction("deleted = false")
@SQLDelete(sql = "update product set deleted = true where id = ?")
public class Product extends AbsUUIDEntity {
    private String name;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Attachment attachment;
    private LocalTime preparationTime;
    @ManyToMany
    private List<Ingredient> ingredients;
    @ManyToOne
    private User author;
    @OneToMany
    private List<Step> steps;
    @OneToMany
    private List<Rating> ratings;
    private Double overallRating;
    @OneToMany
    private List<Review> review;
}
