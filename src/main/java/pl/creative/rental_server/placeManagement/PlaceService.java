package pl.creative.rental_server.placeManagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.Place;
import pl.creative.rental_server.exception.notFound.PlaceNotFound;
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

    @Transactional
    public GetPlaceDto addPlace(InputPlaceDto inputPlaceDto) {
        Place place = placeMapper.mapInputPlaceDtoToPlace(inputPlaceDto);
        String id = randomIdHandler.generateUniqueIdFromTable(placeRepository);
        place.setId(id);
        Place savedPlace = placeRepository.save(place);
//        log.info("Created and saved place",place);
        return placeMapper.mapPlaceToGetPlaceDto(savedPlace);
    }

    public List<GetPlaceDto> getPlaces(){
        List<GetPlaceDto> listOfGetPlaceDto = new ArrayList<>();
        Iterable<Place> places = placeRepository.findAll();
        for (Place place : places) {
            listOfGetPlaceDto.add(placeMapper.mapPlaceToGetPlaceDto(place));
        }
        return listOfGetPlaceDto;
    }

    @Transactional
    public void deletePlace(String placeId, String commentToEvent) {
        Optional<Place> optionalPlace = placeRepository.findById(placeId);
        if(optionalPlace.isPresent()){
            Place place = optionalPlace.get();
            List<Item> items = place.getItems();
            for (Item item : items) {
                itemService.updatePlaceOfItem(item.getId(),"0", commentToEvent); //changing place of items to "abstractPlace"
            }
            placeRepository.delete(place);
        }else {
            log.error("You can't delete place with {} id because does not exist", placeId);
            throw new PlaceNotFound(String.format("You can't delete place with %s id because does not exist", placeId));
        }
    }

    @Transactional
    public EditPlaceDto updatePlace(InputPlaceDto inputPlaceDto, String placeId) {
        Optional<Place> place = placeRepository.findById(placeId);
        if(place.isPresent()){
            String placeName = inputPlaceDto.getName();
            String placeDescription = inputPlaceDto.getDescription();
            placeRepository.updatePlace(placeId, placeName, placeDescription);
            return new EditPlaceDto(placeId,placeName,placeDescription);
        }else{
            log.error("Place with id {} does not exists", placeId);
            throw new PlaceNotFound(String.format("Place with such id %s does not exist", placeId));
        }

    }
}
