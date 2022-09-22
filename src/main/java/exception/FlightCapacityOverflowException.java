package exception;

public class FlightCapacityOverflowException extends RuntimeException{
    public FlightCapacityOverflowException() {
        super();
    }

    public FlightCapacityOverflowException(String message) {
        super(message);
    }
}
