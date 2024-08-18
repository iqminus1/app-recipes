package up.pdp.apprecipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class ProductCrudDto {
    private String name;
    private UUID categoryId;
    private UUID attachmentId;
    private LocalTime preparationTime;
    private List<UUID> ingredientIds;
    private UUID authorId;
    private List<UUID> stepIds;
}
