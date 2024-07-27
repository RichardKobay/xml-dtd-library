package dev.soriane.dtdxmlparser.exceptions;

public class ElementDoesNotExistsException extends Exception {
    public ElementDoesNotExistsException() {
        super();
    }

    public ElementDoesNotExistsException(String message) {
        super(message);
    }

    public ElementDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementDoesNotExistsException(Throwable cause) {
        super(cause);
    }
}
