package pl.creative.rental_server.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.creative.rental_server.entities.Account;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.Place;
import pl.creative.rental_server.entities.StatusOfItem;

import javax.transaction.Transactional;
import java.util.List;

public interface ItemRepository extends PagingAndSortingRepository<Item, String> {
    @Query("SELECT item FROM Item item WHERE item.statusOfItem = :statusOfItem")
    List<Item> getItemsByStatusOfItem(StatusOfItem statusOfItem);

//    @Query("SELECT item FROM Item item WHERE item.borrowedBy IS NOT NULL")
    List<Item> getAllByBorrowedByIsNotNull();

    List<Item> getAllByBorrowedByIsNull();

    @Query("UPDATE Item item SET item.statusOfItem = :statusOfItem WHERE item.id = :itemId")
    @Modifying
    @Transactional
    void updateStatusOfItem(String itemId, StatusOfItem statusOfItem);

    @Query("UPDATE Item item SET item.borrowedBy = :borrowedBy WHERE item.id = :itemId")
    @Modifying
    @Transactional
    void changeBorrowerOfItem(String itemId, Account borrowedBy);

    @Query("UPDATE Item item SET item.place = :place WHERE item.id = :itemId")
    @Modifying
    @Transactional
    void changePlaceOfItem(String itemId, Place place);
}
