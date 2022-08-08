package pl.creative.rental_server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.entities.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
}
