package pl.creative.rental_server.core.global.exception.notFound;

public class AccountNotFound extends NotFoundException{
    public AccountNotFound(String message) {
        super(message);
    }
}
