package up.pdp.apprecipes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class ReviewCRUDDto {
    private UUID authorId;
    private UUID productId;
    private String text;
}
