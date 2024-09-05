package up.pdp.apprecipes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class StepCRUDDto {
    private UUID id;
    private Integer step;
    private String description;
}
