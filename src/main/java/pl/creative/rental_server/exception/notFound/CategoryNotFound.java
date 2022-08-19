package pl.creative.rental_server.exception.notFound;

public class CategoryNotFound extends NotFoundException {
    public CategoryNotFound(String errorMessage) {
        super(errorMessage);
    }
}
