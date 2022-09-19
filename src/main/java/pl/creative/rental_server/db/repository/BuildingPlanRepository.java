package pl.creative.rental_server.db.repository;

import org.springframework.data.repository.CrudRepository;
import pl.creative.rental_server.db.entities.BuildingPlan;

public interface BuildingPlanRepository extends CrudRepository<BuildingPlan, Long> {
}
