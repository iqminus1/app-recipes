package up.pdp.apprecipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import up.pdp.apprecipes.model.Ingredient;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IngredientDto {
    private UUID attachmentId;
    private String name;

    public IngredientDto(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.attachmentId = ingredient.getAttachment().getId();
    }
}
