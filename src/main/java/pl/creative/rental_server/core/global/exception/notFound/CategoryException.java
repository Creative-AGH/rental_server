package pl.creative.rental_server.core.global.exception.notFound;

public class CategoryException extends NotFoundException{
    public CategoryException(String message) {
        super(message);
    }
}
