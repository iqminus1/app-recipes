package up.pdp.apprecipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class IngredientCRUDDto {
    private UUID attachmentId;
    private String name;
}
