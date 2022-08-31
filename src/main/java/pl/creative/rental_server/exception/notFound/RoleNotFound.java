package pl.creative.rental_server.exception.notFound;

public class RoleNotFound extends NotFoundException{
    public RoleNotFound(String message) {
        super(message);
    }
}
