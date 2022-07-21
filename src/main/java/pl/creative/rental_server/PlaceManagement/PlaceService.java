package pl.creative.rental_server.PlaceManagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.Entities.Area;
import pl.creative.rental_server.Entities.Place;
import pl.creative.rental_server.Handlers.RandomIdHandler;
import pl.creative.rental_server.Repository.AreaRepository;
import pl.creative.rental_server.Repository.PlaceRepository;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final RandomIdHandler randomIdHandler;
    private final PlaceMapper placeMapper;
    private final AreaRepository areaRepository;

    @Transactional
    public void createPlace(InputPlaceDto inputPlaceDto,String areaId) {
        Place place = placeMapper.mapInputPlaceDtoToPlace(inputPlaceDto);
        String id = randomIdHandler.generateUniqueIdFromTable(placeRepository);
        place.setId(id);
        areaRepository.findById(areaId).ifPresent(e -> e.addPlace(place));
        placeRepository.save(place);
        log.info("Created and saved place {}",place);
    }
    public void editPlace(InputPlaceDto inputPlaceDto) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Place place = placeMapper.mapInputPlaceDtoToPlace(inputPlaceDto);
        placeRepository.save(place);
        log.info("Created and saved place {}",place);
    }
}
