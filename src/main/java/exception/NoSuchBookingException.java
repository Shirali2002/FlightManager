package exception;

public class NoSuchBookingException extends RuntimeException{
  public NoSuchBookingException() {
    super();
  }

  public NoSuchBookingException(String message, Throwable cause) {
    super(message, cause);
  }
}
