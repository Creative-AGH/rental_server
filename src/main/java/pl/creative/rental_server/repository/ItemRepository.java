package pl.creative.rental_server.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.StatusOfItem;

import javax.transaction.Transactional;
import java.util.List;

public interface ItemRepository extends PagingAndSortingRepository<Item, String> {
    @Query("SELECT item FROM Item item WHERE item.statusOfItem = :statusOfItem")
    List<Item> getItemsByStatusOfItem(StatusOfItem statusOfItem);

    @Query("UPDATE Item item SET item.statusOfItem = :statusOfItem WHERE item.id = :itemId")
    @Modifying
    @Transactional
    void updateStatusOfItem(String itemId, StatusOfItem statusOfItem);
}
