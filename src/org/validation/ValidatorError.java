package org.validation;

public class ValidatorError {

    private String message;

    public ValidatorError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
