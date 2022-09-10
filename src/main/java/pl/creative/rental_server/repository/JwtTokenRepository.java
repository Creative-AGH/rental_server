package pl.creative.rental_server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.entities.JwtToken;

public interface JwtTokenRepository extends PagingAndSortingRepository<JwtToken, String> {
}
