package up.pdp.apprecipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.model.Ingredient;
import up.pdp.apprecipes.model.Product;
import up.pdp.apprecipes.model.Step;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class ProductDto {
    private String name;
    private UUID categoryId;
    private UUID attachmentId;
    private LocalTime preparationTime;
    private List<UUID> ingredientIds;
    private UUID authorId;
    private List<UUID> stepIds;

    public ProductDto(Product product) {
        this.name = product.getName();
        this.categoryId = product.getCategory().getId();
        this.attachmentId = product.getAttachment().getId();
        this.preparationTime = product.getPreparationTime();
        this.ingredientIds = product.getIngredients()
                .stream()
                .map(Ingredient::getId)
                .toList();
        this.authorId = product.getAuthor().getId();
        this.stepIds = product.getSteps()
                .stream()
                .map(Step::getId)
                .toList();
    }
}
