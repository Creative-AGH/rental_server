package pl.creative.rental_server.core.global.exception.notFound;

public class NotFoundException extends RuntimeException{
    public NotFoundException(final String message) {
        super(message);
    }
}
