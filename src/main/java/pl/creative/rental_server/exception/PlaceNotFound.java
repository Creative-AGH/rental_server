package pl.creative.rental_server.exception;

public class PlaceNotFound extends NotFoundException{
    public PlaceNotFound(String errorMessage) {
        super(errorMessage);
    }
}
