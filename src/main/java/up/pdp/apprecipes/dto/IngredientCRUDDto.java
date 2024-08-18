package up.pdp.apprecipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.model.Ingredient;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class IngredientCRUDDto {
    private UUID attachmentId;
    private String name;

    public IngredientCRUDDto(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.attachmentId = ingredient.getAttachment().getId();
    }
}
