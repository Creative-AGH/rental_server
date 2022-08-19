package pl.creative.rental_server.exception.notFound;

public class ItemNotFound extends NotFoundException {
    public ItemNotFound(String errorMessage) {
        super(errorMessage);
    }
}
