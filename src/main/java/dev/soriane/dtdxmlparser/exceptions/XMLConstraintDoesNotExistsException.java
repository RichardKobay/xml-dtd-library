package dev.soriane.dtdxmlparser.exceptions;

public class XMLConstraintDoesNotExistsException extends Exception {
    public XMLConstraintDoesNotExistsException() {
        super();
    }

    public XMLConstraintDoesNotExistsException(String message) {
        super(message);
    }

    public XMLConstraintDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLConstraintDoesNotExistsException(Throwable cause) {
        super(cause);
    }
}
