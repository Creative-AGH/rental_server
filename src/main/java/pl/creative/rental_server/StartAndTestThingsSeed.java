package pl.creative.rental_server;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.creative.rental_server.db.entities.*;
import pl.creative.rental_server.core.global.handlersAndUtils.RandomIdHandler;
import pl.creative.rental_server.db.repository.*;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StartAndTestThingsSeed {
    private final ItemRepository itemRepository;
    private final PlaceRepository placeRepository;
    private final CategoryRepository categoryRepository;
    private final RandomIdHandler randomIdHandler;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public void fillData() {
        createTestAccount();
        createPlace();
        createTestCategory();//TODO REMOVE ALL TEST DATA BEFORE PRODUCTION
        createTestItem();
        createTestPlace();
    }

    private void createTestPlace() {
        if (placeRepository.count() <= 1) { //<=1 because we create one place with id 0
            placeRepository.save(new Place("place1", "placeName1", "placeDescription1"));
            placeRepository.save(new Place("string", "placeNameString", "placeDescriptionString"));
            placeRepository.save(new Place("place2", "placeName2", "placeDescription2"));
            placeRepository.save(new Place("place3", "placeName3", "placeDescription3"));
            placeRepository.save(new Place("place4", "placeName4", "placeDescription4"));
        }
    }

    private void createPlace() {
        Optional<Place> optional = placeRepository.findById("0"); // Initial abstractPlace
        if (optional.isEmpty()) {
            placeRepository.save(new Place("0", "emptyAbstractPlace", "This place contains items without place"));
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

    private void createTestAccount() {
        if (accountRepository.count() == 0) {
            Account admin = new Account(1L, "admin", "Administrator", "Admin", passwordEncoder.encode("admin"));
            admin.setRole(Role.ADMIN);
            accountRepository.save(admin);
            Account moderator = new Account(2L, "Moderator@gmial.com", "Moderator", "Mod", passwordEncoder.encode("mod123"));
            moderator.setRole(Role.MODERATOR);
            accountRepository.save(moderator);
            Account user = new Account(3L, "User@gmial.com", "User", "User", passwordEncoder.encode("user123"));
            user.setRole(Role.USER);
            accountRepository.save(user);
        }
    }




}
