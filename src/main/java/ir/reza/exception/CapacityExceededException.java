package ir.reza.exception;

public class CapacityExceededException  extends BusinessException {

    public CapacityExceededException (String message) {
        super(message);
    }

    public CapacityExceededException (String message, Throwable cause) {
        super(message, cause);
    }
}