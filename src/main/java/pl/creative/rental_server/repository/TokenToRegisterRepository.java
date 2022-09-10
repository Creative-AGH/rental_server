package pl.creative.rental_server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.entities.TokenToRegister;

public interface TokenToRegisterRepository extends PagingAndSortingRepository<TokenToRegister, String> {
}