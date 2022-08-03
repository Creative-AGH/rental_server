package pl.creative.rental_server.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.Entities.TypeOfItem;

public interface TypeOfItemRepository extends PagingAndSortingRepository<TypeOfItem, String> {
}
