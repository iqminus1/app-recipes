package up.pdp.apprecipes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.model.enums.Emotion;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class ReviewEmotionDto {
    private UUID id;
    private UUID authorId;
    private Emotion emotion;
}
