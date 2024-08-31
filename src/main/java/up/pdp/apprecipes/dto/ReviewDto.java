package up.pdp.apprecipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.model.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class ReviewDto {
    private UUID id;
    private UUID authorId;
    private UUID productId;
    private String text;
    private LocalDateTime time;
    private long likes;
    private long dislikes;

    public static List<ReviewDto> of(Product product) {
        return product.getReview()
                .stream()
                .map(r -> new ReviewDto(
                                r.getId(),
                                r.getAuthor().getId(),
                                product.getId(),
                                r.getText(),
                                r.getTime(),
                                r.getLikes(),
                                r.getDislikes()
                        )
                )
                .toList();
    }
}
