package pl.creative.rental_server.core.global.exception;

public class NotSentException extends RuntimeException {
    public NotSentException(String message) {
        super(message);
    }
}
