package dev.soriane.dtdxmlparser.exceptions;

public class ElementAlreadyExistsException extends Exception {
    public ElementAlreadyExistsException() {
        super();
    }

    public ElementAlreadyExistsException(String message) {
        super(message);
    }

    public ElementAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}
