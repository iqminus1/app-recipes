package up.pdp.apprecipes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryCRUDDto {
    private String name;
    private String description;
}
