package pl.creative.rental_server.core.global.exception.notFound;

public class ItemNotFound extends NotFoundException {
    public ItemNotFound(String errorMessage) {
        super(errorMessage);
    }
}
