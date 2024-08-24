package up.pdp.apprecipes.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@AllArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private String message;

    public static NotFoundException errorById(String className, Object id) {
        return new NotFoundException(className + " not found by id -> " + id);
    }

    public static NotFoundException error(String m) {
        return new NotFoundException(m + " not found");
    }
}
