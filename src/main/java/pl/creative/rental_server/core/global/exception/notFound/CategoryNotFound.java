package pl.creative.rental_server.core.global.exception.notFound;

public class CategoryNotFound extends NotFoundException {
    public CategoryNotFound(String errorMessage) {
        super(errorMessage);
    }
}
