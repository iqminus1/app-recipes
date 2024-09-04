package up.pdp.apprecipes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.model.Category;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CategoryDto {
    private UUID id;
    private String name;
    private String description;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }
}
