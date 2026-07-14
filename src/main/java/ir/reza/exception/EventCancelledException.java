package ir.reza.exception;

public class EventCancelledException extends BusinessException {

    public EventCancelledException(String message) {
        super(message);
    }

    public EventCancelledException(String message, Throwable cause) {
        super(message, cause);
    }
}