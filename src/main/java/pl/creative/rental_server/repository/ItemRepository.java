package pl.creative.rental_server.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.StatusOfItem;

import java.util.List;

public interface ItemRepository extends PagingAndSortingRepository<Item, String> {
    @Query("SELECT item FROM Item item WHERE item.statusOfItem = :statusOfItemCode")
    List<Item> getItemsByStatusOfItem(StatusOfItem statusOfItemCode);

}
