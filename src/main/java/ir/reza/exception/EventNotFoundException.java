package ir.reza.exception;

public class EventNotFoundException extends BusinessException {

    public EventNotFoundException(String message) {
        super(message);
    }

    public EventNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}