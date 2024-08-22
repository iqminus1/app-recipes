package up.pdp.apprecipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.model.Rating;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class RatingDto {
    private UUID raterId;
    private UUID productId;
    private Double rating;

    public RatingDto(Rating rating) {
        this.raterId = rating.getRater().getId();
        this.productId = rating.getProduct().getId();
        this.rating = rating.getRating();
    }
}
