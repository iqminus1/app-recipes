package up.pdp.apprecipes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.model.Ingredient;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class IngredientDto {
    private UUID id;
    private UUID attachmentId;
    private String name;

    public IngredientDto(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.attachmentId = ingredient.getAttachment().getId();
        this.name = ingredient.getName();
    }
}
