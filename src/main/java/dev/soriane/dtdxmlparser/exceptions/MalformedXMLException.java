package dev.soriane.dtdxmlparser.exceptions;

public class MalformedXMLException extends Exception {
    public MalformedXMLException() {
        super();
    }

    public MalformedXMLException(String message) {
        super(message);
    }

    public MalformedXMLException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedXMLException(Throwable cause) {
        super(cause);
    }
}
