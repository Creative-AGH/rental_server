package pl.creative.rental_server.core.features.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.creative.rental_server.core.features.moderator.item.dto.GetItemDto;
import pl.creative.rental_server.core.features.moderator.place.PlaceService;
import pl.creative.rental_server.core.features.moderator.place.dto.GetPlaceDto;
import pl.creative.rental_server.docs.user.PlaceUserApi;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaceUserController implements PlaceUserApi {
    private final PlaceService placeService;

    @Override
    public ResponseEntity<GetPlaceDto> getPlaceById(String placeId) {
        return ResponseEntity.ok(placeService.getPlaceById(placeId));
    }

    @Override
    public ResponseEntity<List<GetPlaceDto>> getAllPlaces() {
        return ResponseEntity.ok(placeService.getAllPlaces());
    }

    @Override
    public ResponseEntity<List<GetItemDto>> getAllItemsByPlaceId(String placeId) {
        return ResponseEntity.ok(placeService.getAllItemsByPlaceId(placeId));
    }
}
