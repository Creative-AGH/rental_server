package pl.creative.rental_server;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.creative.rental_server.entities.Area;
import pl.creative.rental_server.entities.Category;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.Place;
import pl.creative.rental_server.handlers.RandomIdHandler;
import pl.creative.rental_server.repository.AreaRepository;
import pl.creative.rental_server.repository.ItemRepository;
import pl.creative.rental_server.repository.PlaceRepository;
import pl.creative.rental_server.repository.CategoryRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StartAndTestThingsSeed {
    private final AreaRepository areaRepository;
    private final ItemRepository itemRepository;
    private final PlaceRepository placeRepository;
    private final CategoryRepository categoryRepository;
    private final RandomIdHandler randomIdHandler;

    public void fillData() {
        createArea();
        createTestCategory();//TODO REMOVE ALL TEST DATA BEFORE PRODUCTION
        createTestItem();
        createTestPlace();
    }

    private void createTestPlace() {
        if (placeRepository.count() == 0) {
            placeRepository.save(new Place("place1", "placeName1", "placeDescription1"));
            placeRepository.save(new Place("place2", "placeName2", "placeDescription2"));
        }
    }

    private void createArea() {
        Optional<Area> optional = areaRepository.findById(0); // Initial area
        if (optional.isEmpty()) {
            areaRepository.save(new Area(0));
        }
    }

    private void createTestCategory() {
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new Category("category1", "categoryName1", "categoryDescription1"));
            categoryRepository.save(new Category("category2", "categoryName2", "categoryDescription2"));
        }
    }

    private void createTestItem() {
        if (itemRepository.count() == 0) {
            itemRepository.save(new Item("item1", "itemName1"));
            itemRepository.save(new Item("item2", "itemName2"));
            itemRepository.save(new Item("item3", "itemName3"));
            itemRepository.save(new Item("item4", "itemName4"));
        }
    }

}
