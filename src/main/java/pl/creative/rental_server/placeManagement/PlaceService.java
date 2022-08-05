package pl.creative.rental_server.placeManagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.Place;
import pl.creative.rental_server.exception.notFound.PlaceNotFound;
import pl.creative.rental_server.handlers.AreaInsideManagement;
import pl.creative.rental_server.handlers.RandomIdHandler;
import pl.creative.rental_server.placeManagement.dto.EditPlaceDto;
import pl.creative.rental_server.placeManagement.dto.InnerPlaceDto;
import pl.creative.rental_server.placeManagement.dto.InputPlaceDto;
import pl.creative.rental_server.placeManagement.dto.PlaceMapper;
import pl.creative.rental_server.repository.ItemRepository;
import pl.creative.rental_server.repository.PlaceRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final RandomIdHandler randomIdHandler;
    private final PlaceMapper placeMapper;

    private final ItemRepository itemRepository;
    private final AreaInsideManagement areaInsideManagement;

    @Transactional
    public String createPlace(InputPlaceDto inputPlaceDto) {
        Place place = placeMapper.mapInputPlaceDtoToPlace(inputPlaceDto);
        String id = randomIdHandler.generateUniqueIdFromTable(placeRepository);
        place.setId(id);
        placeRepository.save(place);
        areaInsideManagement.addPlace(place);// Area with id 0 is
        log.info("Created and saved place {}", place);
        return place.getId();
    }


    public EditPlaceDto editPlace(InnerPlaceDto innerPlaceDto) {
        Optional<Place> placeOptional = placeRepository.findById(innerPlaceDto.getPlaceId());
        if (placeOptional.isPresent()) {
            Place place = placeOptional.get();
            areaInsideManagement.detachPlaceFromArea(place);

            Place editedPlace = placeMapper.mapInnerPlaceDtoToPlace(innerPlaceDto);
            log.info("Editing place {} to {}", place, editedPlace);
            placeRepository.delete(place);
            placeRepository.save(editedPlace);
            areaInsideManagement.addPlace(place);
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

        place.getItems().forEach(areaInsideManagement::addUnusedItem);
        place.setItems(null);
        areaInsideManagement.detachPlaceFromArea(place);
        placeRepository.save(place);
        log.info("Deleting {} ", place);
        placeRepository.delete(place);

    }

}
