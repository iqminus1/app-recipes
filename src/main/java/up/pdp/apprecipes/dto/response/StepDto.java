package up.pdp.apprecipes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import up.pdp.apprecipes.model.Step;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class StepDto {
    private UUID id;
    private Integer step;
    private String description;

    public StepDto(Step step) {
        this.id = step.getId();
        this.step = step.getStep();
        this.description = step.getDescription();
    }
}
