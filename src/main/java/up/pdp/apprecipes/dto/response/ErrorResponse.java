package up.pdp.apprecipes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
