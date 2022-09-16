package pl.creative.rental_server.db.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.db.entities.JwtToken;

public interface JwtTokenRepository extends PagingAndSortingRepository<JwtToken, String> {
}
