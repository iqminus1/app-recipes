package up.pdp.apprecipes.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.dto.RatingDto;
import up.pdp.apprecipes.model.Product;
import up.pdp.apprecipes.model.ProductIngredient;
import up.pdp.apprecipes.model.Step;
import up.pdp.apprecipes.model.templates.AbsUUIDEntity;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class ProductDto {
    private UUID id;
    private String name;
    private String categoryName;
    private UUID attachmentId;

    @Schema(type = "string", format = "time", example = "13:45:00", description = "Time in HH:mm:ss format")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime preparationTime;
    private Map<UUID, Long> ingredients;
    private UUID authorId;
    private List<UUID> stepIds;
    private List<RatingDto> ratings;
    private List<UUID> reviewIds;
    private Double overallRating;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.categoryName = product.getCategory().getName();
        this.attachmentId = product.getAttachment().getId();
        this.preparationTime = product.getPreparationTime();
        this.ingredients = product.getIngredients()
                .stream()
                .collect(Collectors.toMap(
                        productIngredient -> productIngredient.getIngredient().getId(),
                        ProductIngredient::getQuantity
                ));
        this.authorId = product.getAuthor().getId();
        this.stepIds = product.getSteps()
                .stream()
                .map(Step::getId)
                .toList();
        this.reviewIds = product.getReview() != null
                ? product.getReview().stream().map(AbsUUIDEntity::getId).toList()
                : null;
        this.overallRating = product.getOverallRating();
    }
}
