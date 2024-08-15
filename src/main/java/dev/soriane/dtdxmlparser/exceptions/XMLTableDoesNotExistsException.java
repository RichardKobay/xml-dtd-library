package dev.soriane.dtdxmlparser.exceptions;

public class XMLTableDoesNotExistsException extends Exception {
    public XMLTableDoesNotExistsException() {
        super();
    }

    public XMLTableDoesNotExistsException(String message) {
        super(message);
    }

    public XMLTableDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLTableDoesNotExistsException(Throwable cause) {
        super(cause);
    }
}
