package pl.creative.rental_server.placeManagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.Place;
import pl.creative.rental_server.exception.notFound.PlaceNotFound;
import pl.creative.rental_server.handlers.AreaInsideManagement;
import pl.creative.rental_server.handlers.RandomIdHandler;
import pl.creative.rental_server.itemManagement.ItemService;
import pl.creative.rental_server.placeManagement.dto.*;
import pl.creative.rental_server.repository.ItemRepository;
import pl.creative.rental_server.repository.PlaceRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final RandomIdHandler randomIdHandler;
    private final PlaceMapper placeMapper;
    private final ItemService itemService;

    private final ItemRepository itemRepository;
    private final AreaInsideManagement areaInsideManagement;

    @Transactional
    public PlaceIdDto createPlace(InputPlaceDto inputPlaceDto) {
        Place place = placeMapper.mapInputPlaceDtoToPlace(inputPlaceDto);
        String id = randomIdHandler.generateUniqueIdFromTable(placeRepository);
        place.setId(id);
        placeRepository.save(place);
        areaInsideManagement.addPlace(place);// Area with id 0 is
        log.info("Created and saved place",place);
        return new PlaceIdDto(place.getId());
    }

    public List<GetPlaceDto> getPlaces(){
        List<GetPlaceDto> listOfGetPlaceDto = new ArrayList<>();
        Iterable<Place> places = placeRepository.findAll();
        for (Place place : places) {
            listOfGetPlaceDto.add(placeMapper.mapPlaceToGetPlaceDto(place));
        }
        return listOfGetPlaceDto;
    }

    //FIXME we can not update a place when the place has items
    //Is this functionality even necessary?
    @Transactional
    public EditPlaceDto updatePlace(InputPlaceDto dto, String placeId) {
        InnerPlaceDto innerPlaceDto = placeMapper.mapInputPlaceDtoToInnerPlaceDto(dto, placeId);
        Optional<Place> placeOptional = placeRepository.findById(innerPlaceDto.getPlaceId());
        if (placeOptional.isPresent()) {
            Place place = placeOptional.get();
//            areaInsideManagement.detachPlaceFromArea(place);

            Place editedPlace = placeMapper.mapInnerPlaceDtoToPlace(innerPlaceDto);
            log.info("Editing place {} to {}", place, editedPlace);
            placeRepository.delete(place);
            placeRepository.save(editedPlace);
//            areaInsideManagement.addPlace(place);
            log.info("Successfully edited place {} to {}", place, editedPlace);
            return placeMapper.mapPlaceToEditPlaceDto(editedPlace);
        } else {
            log.error("Place with id {} does not exists", innerPlaceDto.getPlaceId());
            throw new PlaceNotFound(String.format("Place with id %s not found", innerPlaceDto.getPlaceId()));
        }
    }

    @Transactional
    public void deletePlace(String placeId) {
        placeRepository.findById(placeId).ifPresentOrElse(this::removePlace,
                () -> {
            log.error("You can't delete place with {} id because does not exist", placeId);
            throw new PlaceNotFound(String.format("You can't delete place with %s id because does not exist", placeId));
        });
    }

    @Transactional
    public void removePlace(Place place) {
        List<Item> items = place.getItems();
        for (Item item : items) {
            itemService.changePlaceOfItem(item.getId(),"0"); //changing place to "abstractPlace"
        }
//        log.info("Deleting {} ", place); //logger problem
        placeRepository.delete(place);
//        log.info("Restoring items from {} and make it unused {}", place, place.getItems());
//        place.getItems().forEach(areaInsideManagement::addUnusedItem);
//        place.getItems().forEach(Item::changePlaceOfItem("0"));
//        place.setItems(null);
//        areaInsideManagement.detachPlaceFromArea(place);
//        placeRepository.save(place);
//        log.info("Deleting {} ", place);
//        placeRepository.delete(place);

    }

}
