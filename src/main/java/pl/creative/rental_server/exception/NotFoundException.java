package pl.creative.rental_server.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(final String message) {
        super(message);
    }
}
