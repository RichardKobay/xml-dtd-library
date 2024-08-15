package dev.soriane.dtdxmlparser.exceptions;

public class NoConstraintsElementException extends Exception {
    public NoConstraintsElementException() {
        super();
    }

    public NoConstraintsElementException(String message) {
        super(message);
    }

    public NoConstraintsElementException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoConstraintsElementException(Throwable cause) {
        super(cause);
    }
}
