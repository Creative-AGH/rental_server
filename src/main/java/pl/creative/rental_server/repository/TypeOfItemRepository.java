package pl.creative.rental_server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.entities.TypeOfItem;

public interface TypeOfItemRepository extends PagingAndSortingRepository<TypeOfItem, String> {
}
