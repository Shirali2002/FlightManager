package exception;

public class EmptyDatabaseException extends RuntimeException{
  public EmptyDatabaseException() {
    super();
  }

  public EmptyDatabaseException(String message) {
    super(message);
  }
}
