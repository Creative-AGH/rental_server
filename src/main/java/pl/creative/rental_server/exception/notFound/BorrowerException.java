package pl.creative.rental_server.exception.notFound;

public class BorrowerException extends NotFoundException{
    public BorrowerException(String message) {
        super(message);
    }
}
