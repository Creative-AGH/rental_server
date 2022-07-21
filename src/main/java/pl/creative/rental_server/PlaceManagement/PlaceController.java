package pl.creative.rental_server.PlaceManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequiredArgsConstructor
public class PlaceController implements PlaceApi {
    private final PlaceService placeService;

    public ResponseEntity<?> createPlace(InputPlaceDto dto, String areaId) {
        placeService.createPlace(dto,areaId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> updatePlace(InputPlaceDto dto, String areaId, String placeId) {
        return null;
    }

    @Override
    public ResponseEntity<?> deletePlace(String areaId, String placeId) {
        return null;
    }

    public ResponseEntity<?> getArea(String areaId, String placeId) {
        return null;
    }
}
