package up.pdp.apprecipes.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResultDto<T> implements Serializable {
    private boolean success;
    private T data;
    private String errorMessage;
    private List<FieldErrorDto> fieldErrors;

    public static <T> ApiResultDto<T> success(T data) {
        ApiResultDto<T> apiResultDTO = new ApiResultDto<>();
        apiResultDTO.setSuccess(true);
        apiResultDTO.setData(data);
        return apiResultDTO;
    }

    public static ApiResultDto<?> error(String errorMessage) {
        ApiResultDto<?> apiResultDTO = new ApiResultDto<>();
        apiResultDTO.setErrorMessage(errorMessage);
        return apiResultDTO;
    }

    public static ApiResultDto<?> error(MethodArgumentNotValidException exception) {
        List<FieldErrorDto> fieldErrors = new ArrayList<>();
        for (FieldError fieldError : exception.getFieldErrors()) {
            fieldErrors.add(new FieldErrorDto(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        ApiResultDto<?> apiResultDTO = new ApiResultDto<>();
        apiResultDTO.setFieldErrors(fieldErrors);
        return apiResultDTO;
    }
}
