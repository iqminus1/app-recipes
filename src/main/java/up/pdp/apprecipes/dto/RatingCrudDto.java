package up.pdp.apprecipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class RatingCrudDto {
    private UUID raterId;
    private UUID productId;
    private Double rating;
}
