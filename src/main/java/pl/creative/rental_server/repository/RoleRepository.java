package pl.creative.rental_server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.entities.Role;

import java.util.Optional;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
