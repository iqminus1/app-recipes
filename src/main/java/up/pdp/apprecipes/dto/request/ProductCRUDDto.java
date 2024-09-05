package up.pdp.apprecipes.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.dto.RatingDto;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class ProductCRUDDto {
    private String name;
    private String categoryName;
    private UUID attachmentId;
    @Schema(type = "string", format = "time", example = "13:45:00", description = "Time in HH:mm:ss format")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime preparationTime;
    private List<UUID> ingredientIds;
    private UUID authorId;
    private List<UUID> stepIds;
    private List<RatingDto> ratings;
    private List<UUID> reviewIds;
    private Double overallRating;
}
