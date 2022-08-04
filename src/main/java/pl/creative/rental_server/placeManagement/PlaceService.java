package pl.creative.rental_server.placeManagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.Area;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.Place;
import pl.creative.rental_server.placeManagement.dto.EditPlaceDto;
import pl.creative.rental_server.placeManagement.dto.InnerPlaceDto;
import pl.creative.rental_server.placeManagement.dto.InputPlaceDto;
import pl.creative.rental_server.placeManagement.dto.PlaceMapper;
import pl.creative.rental_server.exception.PlaceNotFound;
import pl.creative.rental_server.handlers.RandomIdHandler;
import pl.creative.rental_server.repository.AreaRepository;
import pl.creative.rental_server.repository.ItemRepository;
import pl.creative.rental_server.repository.PlaceRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final RandomIdHandler randomIdHandler;
    private final PlaceMapper placeMapper;
    private final AreaRepository areaRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public String createPlace(InputPlaceDto inputPlaceDto) {
        Place place = placeMapper.mapInputPlaceDtoToPlace(inputPlaceDto);
        String id = randomIdHandler.generateUniqueIdFromTable(placeRepository);
        place.setId(id);
        areaRepository.findById(0).ifPresent(e -> e.addPlace(place));// Area with id 0 is
        placeRepository.save(place);
        log.info("Created and saved place {}", place);
        return place.getId();
    }


    public EditPlaceDto editPlace(InnerPlaceDto innerPlaceDto) throws PlaceNotFound {
        Optional<Place> placeOptional = placeRepository.findById(innerPlaceDto.getPlaceId());
        if (placeOptional.isPresent()) {
            Place place = placeOptional.get();
            Area area = areaRepository.findById(0).get();
            area.getPlaces().remove(place);

            Place editedPlace = placeMapper.mapInnerPlaceDtoToPlace(innerPlaceDto);
            log.info("Editing place {} to {}", place, editedPlace);
            placeRepository.delete(place);
            placeRepository.save(editedPlace);
            area.addPlace(editedPlace);
            areaRepository.save(area);
            return placeMapper.mapPlaceToEditPlaceDto(editedPlace);
        } else {
            log.error("Place with id {} not exists", innerPlaceDto.getPlaceId());
            throw new PlaceNotFound(String.format("Place with id %s not found", innerPlaceDto.getPlaceId()));
        }
    }

    @Transactional
    public void deletePlace(String id) {
        placeRepository.findById(id).ifPresentOrElse(this::removePlace, () -> log.error("You can't delete palce with {} id ", id));
    }

    public void removePlace(Place place) {
        log.info("Restoring items from {} and make it unused {}", place, place.getItems());
        Area area = areaRepository.findById(0).get();
        place.getItems().forEach(unUsedItem -> area.getUnusedItems().add(unUsedItem));
        place.setItems(null);
        area.getPlaces().remove(place);
        placeRepository.save(place);
        log.info("Deleting {} ", place);
        placeRepository.delete(place);

    }

}
