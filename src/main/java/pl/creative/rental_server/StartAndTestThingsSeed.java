package pl.creative.rental_server;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.creative.rental_server.entities.Area;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.Place;
import pl.creative.rental_server.entities.TypeOfItem;
import pl.creative.rental_server.handlers.RandomIdHandler;
import pl.creative.rental_server.repository.AreaRepository;
import pl.creative.rental_server.repository.ItemRepository;
import pl.creative.rental_server.repository.PlaceRepository;
import pl.creative.rental_server.repository.TypeOfItemRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StartAndTestThingsSeed {
    private final AreaRepository areaRepository;
    private final ItemRepository itemRepository;
    private final PlaceRepository placeRepository;
    private final TypeOfItemRepository typeOfItemRepository;
    private final RandomIdHandler randomIdHandler;

    public void fillData() {
        createArea();
        createTestTypeOfItem();//TODO REMOVE ALL TEST DATA BEFORE PRODUCTION
        createTestItem();
        createTestPlace();
    }

    private void createTestPlace() {
        if (placeRepository.count() == 0) {
            placeRepository.save(new Place("test1", "place", "place23"));
            placeRepository.save(new Place("test2", "place2", "place233"));
        }
    }

    private void createArea() {
        Optional<Area> optional = areaRepository.findById(0); // Initial area
        if (optional.isEmpty()) {
            areaRepository.save(new Area(0));
        }
    }

    private void createTestTypeOfItem() {
        if (typeOfItemRepository.count() == 0) {
            typeOfItemRepository.save(new TypeOfItem("test1", "test", "test"));
            typeOfItemRepository.save(new TypeOfItem("test2", "test2", "test2"));
        }
    }

    private void createTestItem() {
        if (itemRepository.count() == 0) {
            itemRepository.save(new Item("test1", "123"));
            itemRepository.save(new Item("test2", "1234"));
        }
    }

}
