package pl.creative.rental_server.core.features.moderator.place;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.core.features.moderator.item.ItemService;
import pl.creative.rental_server.core.features.moderator.item.dto.GetItemDto;
import pl.creative.rental_server.core.features.moderator.item.dto.ItemMapper;
import pl.creative.rental_server.core.features.moderator.place.dto.EditPlaceDto;
import pl.creative.rental_server.core.features.moderator.place.dto.GetPlaceDto;
import pl.creative.rental_server.core.features.moderator.place.dto.InputPlaceDto;
import pl.creative.rental_server.core.features.moderator.place.dto.PlaceMapper;
import pl.creative.rental_server.core.global.exception.notFound.PlaceNotFound;
import pl.creative.rental_server.core.global.handlersAndUtils.RandomIdHandler;
import pl.creative.rental_server.db.entities.Item;
import pl.creative.rental_server.db.entities.Place;
import pl.creative.rental_server.db.repository.ItemRepository;
import pl.creative.rental_server.db.repository.PlaceRepository;

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
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    @Transactional
    public GetPlaceDto addPlace(InputPlaceDto inputPlaceDto) {
        log.info("Adding new place");
        Place place = placeMapper.mapInputPlaceDtoToPlace(inputPlaceDto);
        String id = randomIdHandler.generateUniqueIdFromTable(placeRepository);
        place.setId(id);
        Place savedPlace = placeRepository.save(place);
        log.info("Successfully created and saved place");
        return placeMapper.mapPlaceToGetPlaceDto(savedPlace);
    }

    public List<GetPlaceDto> getAllPlaces() {
        log.info("Getting all places");
        List<GetPlaceDto> listOfGetPlaceDto = new ArrayList<>();
        Iterable<Place> places = placeRepository.findAll();
        for (Place place : places) {
            listOfGetPlaceDto.add(placeMapper.mapPlaceToGetPlaceDto(place));
        }
        return listOfGetPlaceDto;
    }

    @Transactional
    public void deletePlace(String placeId, String commentToEvent) {
        log.info("Deleting the place");
        Optional<Place> optionalPlace = placeRepository.findById(placeId);
        if (optionalPlace.isPresent()) {
            Place place = optionalPlace.get();
            List<Item> items = place.getItems();
            for (Item item : items) {
                itemService.updatePlaceOfItem(item.getId(), "0", commentToEvent); //changing place of items to "abstractPlace"
            }
            placeRepository.delete(place);
            log.info("Successfully deleted place");
        } else {
            log.error("You can't delete place with that id {} because such id does not exist", placeId);
            throw new PlaceNotFound(String.format("You can't delete place with that id %s because such id does not exist", placeId));
        }
    }

    @Transactional
    public EditPlaceDto updatePlace(InputPlaceDto inputPlaceDto, String placeId) {
        log.info("Updating the place");
        Optional<Place> place = placeRepository.findById(placeId);
        if (place.isPresent()) {
            String placeName = inputPlaceDto.getName();
            String placeDescription = inputPlaceDto.getDescription();
            placeRepository.updatePlace(placeId, placeName, placeDescription);
            log.info("Successfully updated place");
            return new EditPlaceDto(placeId, placeName, placeDescription);
        } else {
            log.error("Place with that id {} does not exist", placeId);
            throw new PlaceNotFound(String.format("Place with that id %s does not exist", placeId));
        }
    }

    public List<GetItemDto> getAllItemsByPlaceId(String placeId) {
        log.info("Getting all items by place id");
        Optional<Place> optionalPlace = placeRepository.findById(placeId);
        if (optionalPlace.isPresent()) {
            return optionalPlace.get().getItems().stream().map(itemMapper::mapItemToGetItemDto).toList();
        } else {
            log.error("Place with that id {} does not exist", placeId);
            throw new PlaceNotFound(String.format("Place with that id %s does not exist", placeId));
        }
    }

    public GetPlaceDto getPlaceById(String placeId) {
        log.info("Getting the place by place id");
        Optional<Place> optionalPlace = placeRepository.findById(placeId);
        if (optionalPlace.isPresent()) {
            return placeMapper.mapPlaceToGetPlaceDto(optionalPlace.get());
        } else {
            log.error("Place with that id {} does not exist", placeId);
            throw new PlaceNotFound(String.format("Place with that id %s does not exist", placeId));
        }
    }
}
