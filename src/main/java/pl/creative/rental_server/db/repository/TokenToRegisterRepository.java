package pl.creative.rental_server.db.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.db.entities.TokenToRegister;

public interface TokenToRegisterRepository extends PagingAndSortingRepository<TokenToRegister, String> {
}
