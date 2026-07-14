package ir.reza.exception;

public class InvalidDataException  extends BusinessException {

    public InvalidDataException (String message) {
        super(message);
    }

    public InvalidDataException (String message, Throwable cause) {
        super(message, cause);
    }
}