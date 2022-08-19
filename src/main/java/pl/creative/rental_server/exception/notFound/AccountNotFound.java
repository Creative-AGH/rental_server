package pl.creative.rental_server.exception.notFound;

public class AccountNotFound extends NotFoundException{
    public AccountNotFound(String message) {
        super(message);
    }
}
