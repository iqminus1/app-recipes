package up.pdp.apprecipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.model.Step;

@AllArgsConstructor
@Getter
public class StepDto {
    private Integer step;
    private String description;

    public StepDto(Step step) {
        this.step = step.getStep();
        this.description = step.getDescription();
    }
}
