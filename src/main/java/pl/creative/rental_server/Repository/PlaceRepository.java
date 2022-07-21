package pl.creative.rental_server.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.Entities.Place;

public interface PlaceRepository extends PagingAndSortingRepository<Place,String> {
}
