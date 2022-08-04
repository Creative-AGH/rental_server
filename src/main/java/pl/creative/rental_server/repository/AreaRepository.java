package pl.creative.rental_server.repository;

import org.springframework.data.repository.CrudRepository;
import pl.creative.rental_server.entities.Area;

public interface AreaRepository extends CrudRepository<Area,Integer> {
}
