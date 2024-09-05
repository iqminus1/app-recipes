package up.pdp.apprecipes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import up.pdp.apprecipes.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class CategoryDto {
    private UUID id;
    private String name;
    private String description;
    private List<ProductDto> products;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.products = new ArrayList<>();
    }
}
