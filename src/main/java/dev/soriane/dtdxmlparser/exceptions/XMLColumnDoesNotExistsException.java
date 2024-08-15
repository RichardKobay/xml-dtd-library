package dev.soriane.dtdxmlparser.exceptions;

public class XMLColumnDoesNotExistsException extends Exception {
    public XMLColumnDoesNotExistsException() {
        super();
    }

    public XMLColumnDoesNotExistsException(String message) {
        super(message);
    }

    public XMLColumnDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLColumnDoesNotExistsException(Throwable cause) {
        super(cause);
    }
}
