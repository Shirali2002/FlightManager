package exception;

public class PasswordNotEqualException extends RuntimeException{
  public PasswordNotEqualException() {
    super();
  }

  public PasswordNotEqualException(String message) {
    super(message);
  }
}
