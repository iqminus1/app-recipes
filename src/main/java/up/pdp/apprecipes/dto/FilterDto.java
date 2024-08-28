package up.pdp.apprecipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.model.Category;
import up.pdp.apprecipes.model.Rating;
import up.pdp.apprecipes.model.enums.TimeFilter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class FilterDto {
    private Category category;
    private Rating rating;
    private TimeFilter timeFilter;
}
