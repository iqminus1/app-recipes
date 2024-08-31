package up.pdp.apprecipes.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String m){
        super("Invalid data in field(s) " + m);
    }
}
