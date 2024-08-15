package dev.soriane.dtdxmlparser.exceptions;

public class ConstraintAlreadyExistsException extends Exception {
    public ConstraintAlreadyExistsException() {
        super();
    }

    public ConstraintAlreadyExistsException(String message) {
        super(message);
    }

    public ConstraintAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConstraintAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
