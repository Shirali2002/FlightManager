package exception;

public class NoFlightMatchedIdException extends RuntimeException{
  public NoFlightMatchedIdException() {
    super();
  }

  public NoFlightMatchedIdException(String message) {
    super(message);
  }
}
