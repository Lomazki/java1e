package org.exceptions;

public class UncheckedException extends RuntimeException {

    private static final long serialVersionUID = 6015787752086380883L;

    public UncheckedException(String message) {
        super(message);
    }

    public UncheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UncheckedException(Throwable cause) {
        super(cause);
    }
}
