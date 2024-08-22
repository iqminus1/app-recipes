package up.pdp.apprecipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.model.Category;

@AllArgsConstructor
@Getter
public class CategoryDto {
    private String name;
    private String description;

    public CategoryDto(Category category) {
        this.name = category.getName();
        this.description = category.getDescription();
    }
}
