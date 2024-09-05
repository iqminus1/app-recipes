package up.pdp.apprecipes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IngredientCRUDDto {
    private UUID attachmentId;
    private String name;
}
