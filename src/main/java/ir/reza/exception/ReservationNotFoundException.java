package ir.reza.exception;

public class ReservationNotFoundException   extends BusinessException {

    public ReservationNotFoundException  (String message) {
        super(message);
    }

    public ReservationNotFoundException  (String message, Throwable cause) {
        super(message, cause);
    }
}