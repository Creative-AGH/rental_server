package pl.creative.rental_server.db.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.db.entities.ItemHistory;

import java.util.List;

public interface ItemHistoryRepository extends PagingAndSortingRepository<ItemHistory, Long> {
    List<ItemHistory> findAllByItemId(String itemId);
}
