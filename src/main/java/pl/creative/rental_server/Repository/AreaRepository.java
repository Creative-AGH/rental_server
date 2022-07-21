package pl.creative.rental_server.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.creative.rental_server.Entities.Area;

public interface AreaRepository extends CrudRepository<Area,String> {
}
