package exception;

public class UnsuccessfulSearchException extends RuntimeException{
  public UnsuccessfulSearchException() {
    super();
  }

  public UnsuccessfulSearchException(String message) {
    super(message);
  }
}
