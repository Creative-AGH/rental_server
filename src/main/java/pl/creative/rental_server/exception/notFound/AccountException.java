package pl.creative.rental_server.exception.notFound;

public class AccountException extends NotFoundException{
    public AccountException(String message) {
        super(message);
    }
}
