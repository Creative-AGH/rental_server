package pl.creative.rental_server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.entities.Item;

public interface ItemRepository extends PagingAndSortingRepository<Item, String> {
}
