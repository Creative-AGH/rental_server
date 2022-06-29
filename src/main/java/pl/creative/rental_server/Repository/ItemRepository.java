package pl.creative.rental_server.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.Entities.Item;

public interface ItemRepository extends PagingAndSortingRepository<Item, String> {
}
