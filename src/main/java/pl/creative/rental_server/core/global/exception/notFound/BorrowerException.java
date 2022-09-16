package pl.creative.rental_server.core.global.exception.notFound;

public class BorrowerException extends NotFoundException{
    public BorrowerException(String message) {
        super(message);
    }
}
