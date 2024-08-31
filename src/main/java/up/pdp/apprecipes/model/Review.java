package up.pdp.apprecipes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import up.pdp.apprecipes.model.templates.AbsUUIDEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@SQLRestriction("deleted = false")
@SQLDelete(sql = "update review set deleted = true where id = ?")
public class Review extends AbsUUIDEntity {
    @ManyToOne
    private User author;
    private String text;
    private LocalDateTime time;
    private long likes;
    private long dislikes;
//
//    @PrePersist
//    protected void onCreate() {
//        time = LocalTime.now();
//    }
}
