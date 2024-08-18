package up.pdp.apprecipes.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import up.pdp.apprecipes.exceptions.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(AlreadyExistsException.class)
//    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException ex) {
//        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
//    }
//
//    @ExceptionHandler(InvalidDataException.class)
//    public ResponseEntity<ErrorResponse> handleInvalidArgumentException(InvalidDataException ex) {
//        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
//    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
