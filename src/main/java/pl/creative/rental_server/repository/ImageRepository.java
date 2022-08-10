package pl.creative.rental_server.repository;

import org.springframework.data.repository.CrudRepository;
import pl.creative.rental_server.entities.Image;

public interface ImageRepository extends CrudRepository<Image, String> {
}
