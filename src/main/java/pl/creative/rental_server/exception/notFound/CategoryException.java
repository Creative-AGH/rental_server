package pl.creative.rental_server.exception.notFound;

public class CategoryException extends NotFoundException{
    public CategoryException(String message) {
        super(message);
    }
}
