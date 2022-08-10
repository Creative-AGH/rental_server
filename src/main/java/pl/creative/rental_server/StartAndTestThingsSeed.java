package pl.creative.rental_server;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.creative.rental_server.entities.*;
import pl.creative.rental_server.handlers.RandomIdHandler;
import pl.creative.rental_server.repository.*;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StartAndTestThingsSeed {
    private final ItemRepository itemRepository;
    private final PlaceRepository placeRepository;
    private final CategoryRepository categoryRepository;
    private final RandomIdHandler randomIdHandler;
    private final AccountRepository accountRepository;

    public void fillData() {
        createTestAccount();
        createPlace();
        createTestCategory();//TODO REMOVE ALL TEST DATA BEFORE PRODUCTION
        createTestItem();
        createTestPlace();
    }

    private void createTestPlace() {
        if (placeRepository.count() <=1) { //<=1 because we create one place with id 0
            placeRepository.save(new Place("place1", "placeName1", "placeDescription1"));
            placeRepository.save(new Place("string", "placeNameString", "placeDescriptionString"));
            placeRepository.save(new Place("place2", "placeName2", "placeDescription2"));
        }
    }

    private void createPlace() {
        Optional<Place> optional = placeRepository.findById("0"); // Initial abstractPlace
        if (optional.isEmpty()) {
            placeRepository.save(new Place("0","emptyAbstractPlace","This place contains items," +
                    " which have not assigned place (item without place)"));
        }
    }



    private void createTestCategory() {
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new Category("category1", "categoryName1", "categoryDescription1"));
            categoryRepository.save(new Category("string", "categoryNameString", "categoryDescriptionString"));
            categoryRepository.save(new Category("category2", "categoryName2", "categoryDescription2"));
        }
    }

    private void createTestItem() {
        if (itemRepository.count() == 0) {
            itemRepository.save(new Item("item1", "itemName1"));
            itemRepository.save(new Item("string", "itemNameString"));
            itemRepository.save(new Item("item2", "itemName2"));
            itemRepository.save(new Item("item3", "itemName3"));
            itemRepository.save(new Item("item4", "itemName4"));
        }
    }

    private void createTestAccount(){
        if(accountRepository.count() == 0){
            accountRepository.save(new Account(1L,"accountName1@gmial.com","name1","surname1","password1"));
            accountRepository.save(new Account(2L,"accountName2@gmial.com","name2","surname2","password2"));
            accountRepository.save(new Account(3L,"accountName3@gmial.com","name3","surname3","password3"));
        }
    }

}
