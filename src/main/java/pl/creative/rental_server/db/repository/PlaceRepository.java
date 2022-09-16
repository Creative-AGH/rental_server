package pl.creative.rental_server.db.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.db.entities.Place;

import javax.transaction.Transactional;

public interface PlaceRepository extends PagingAndSortingRepository<Place,String> {
    @Query(value = "UPDATE Place p SET p.name = :name, p.description = :description WHERE p.id = :placeId")
    @Modifying
    @Transactional
    void updatePlace(String placeId, String name, String description);
}
