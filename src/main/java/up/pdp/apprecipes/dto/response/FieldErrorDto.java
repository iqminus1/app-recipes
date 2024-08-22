package up.pdp.apprecipes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldErrorDto implements Serializable {
    private String field;
    private String errorMessage;
}
