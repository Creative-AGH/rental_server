package pl.creative.rental_server.core.global.exception.notFound;

public class PlaceNotFound extends NotFoundException {
    public PlaceNotFound(String errorMessage) {
        super(errorMessage);
    }
}
