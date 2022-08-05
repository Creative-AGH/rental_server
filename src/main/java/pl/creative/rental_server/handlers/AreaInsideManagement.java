package pl.creative.rental_server.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.creative.rental_server.entities.Area;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.Place;
import pl.creative.rental_server.repository.AreaRepository;
@Component
@Slf4j
public class AreaInsideManagement {
    private final AreaRepository areaRepository;
    private final Area area;

    public AreaInsideManagement(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
        this.area = areaRepository.findById(0).orElse(new Area(0));
    }


    public void addPlace(Place place)
    {
        area.getPlaces().add(place);
        areaRepository.save(area);
        log.info("Successfully added place {} to area",place);
    }
//    public void addUnusedItem(Item item)
//    {
//        area.getItemsWithoutPlace().add(item);
//        areaRepository.save(area);
//        log.info("Successfully added not used item {} to area",item);
//    }

//    public Item detachUnusedItemFromArea(Item item)
//    {
//        area.getItemsWithoutPlace().remove(item);
//        areaRepository.save(area);
//        log.info("Detached item {} from area ",item);
//        return item;
//    }
    public Place detachPlaceFromArea(Place place)
    {
        area.getPlaces().remove(place);
        areaRepository.save(area);
        log.info("Detached place {} from area ",place);
        return place;
    }
}
