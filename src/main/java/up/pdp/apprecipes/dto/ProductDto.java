package up.pdp.apprecipes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(type = "string", format = "time", example = "13:45:00", description = "Time in HH:mm:ss format")
    @JsonFormat(pattern = "HH:mm:ss")
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
