package up.pdp.apprecipes.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.model.Ingredient;
import up.pdp.apprecipes.model.Product;
import up.pdp.apprecipes.model.Rating;
import up.pdp.apprecipes.model.Step;
import up.pdp.apprecipes.model.templates.AbsUUIDEntity;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class ProductDto {
    private UUID id;
    private String name;
    private UUID categoryId;
    private UUID attachmentId;
    @Schema(type = "string", format = "time", example = "13:45:00", description = "Time in HH:mm:ss format")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime preparationTime;
    private List<UUID> ingredientIds;
    private UUID authorId;
    private List<UUID> stepIds;
    private List<Rating> ratings;
    private List<UUID> reviewIds;
    private Double overallRating;

    public ProductDto(Product product) {
        this.id = product.getId();
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
        this.reviewIds = product.getReview() != null ? product.getReview().stream().map(AbsUUIDEntity::getId).toList() : null;
        this.overallRating = product.getOverallRating();
    }
}
