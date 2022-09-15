package exception;

public class UniqueUsernameException extends RuntimeException {
  public UniqueUsernameException() {
    super();
  }

  public UniqueUsernameException(String message) {
    super(message);
  }
}
