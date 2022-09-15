package exception;

public class StrongPasswordException extends RuntimeException{
  public StrongPasswordException() {
    super();
  }

  public StrongPasswordException(String message) {
    super(message);
  }
}
