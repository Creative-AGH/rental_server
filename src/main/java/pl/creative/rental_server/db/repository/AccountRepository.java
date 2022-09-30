package pl.creative.rental_server.db.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.db.entities.Account;

import java.util.Optional;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
    boolean existsAccountByEmail(String email);
    Optional<Account> findByEmail(String email);
}
