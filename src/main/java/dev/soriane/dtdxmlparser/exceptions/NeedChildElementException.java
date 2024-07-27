package dev.soriane.dtdxmlparser.exceptions;

public class NeedChildElementException extends Exception {
    public NeedChildElementException() {
        super();
    }

    public NeedChildElementException(String message) {
        super(message);
    }

    public NeedChildElementException(String message, Throwable cause) {
        super(message, cause);
    }

    public NeedChildElementException(Throwable cause) {
        super(cause);
    }
}
