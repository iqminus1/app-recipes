package up.pdp.apprecipes.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String m) {
        super(m + " already exists");
    }
}