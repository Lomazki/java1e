package rubbish.fromKosta.model;

public class ValidationError {

  String message;

  public ValidationError(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
