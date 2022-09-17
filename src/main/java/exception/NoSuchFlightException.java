package exception;

public class NoSuchFlightException extends RuntimeException{
  public NoSuchFlightException() {
    super();
  }

  public NoSuchFlightException(String message) {
    super(message);
  }
}
