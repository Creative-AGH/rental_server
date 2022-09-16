package pl.creative.rental_server.db.repository;

import org.springframework.data.repository.CrudRepository;
import pl.creative.rental_server.db.entities.Image;

public interface ImageRepository extends CrudRepository<Image, String> {
}
