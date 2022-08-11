package pl.creative.rental_server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.ItemHistory;

import java.util.List;

public interface ItemHistoryRepository extends PagingAndSortingRepository<ItemHistory, Long> {
    List<ItemHistory> findAllByItem(Item item);
}
