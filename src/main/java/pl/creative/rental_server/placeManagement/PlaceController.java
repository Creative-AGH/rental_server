package pl.creative.rental_server.placeManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;
import pl.creative.rental_server.placeManagement.dto.EditPlaceDto;
import pl.creative.rental_server.placeManagement.dto.GetPlaceDto;
import pl.creative.rental_server.placeManagement.dto.InputPlaceDto;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaceController implements PlaceApi {
    private final PlaceService placeService;

    @Override
    public ResponseEntity<GetPlaceDto> addPlace(InputPlaceDto inputPlaceDto) {
        GetPlaceDto savedPlace = placeService.addPlace(inputPlaceDto);
        URI savedPlaceUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPlace.getId())
                .toUri();
        return ResponseEntity.created(savedPlaceUri).body(savedPlace);
    }

    @Override
    public ResponseEntity<GetPlaceDto> getPlaceById(String placeId) {
        return ResponseEntity.ok(placeService.getPlaceById(placeId));
    }

    @Override
    public ResponseEntity<List<GetPlaceDto>> getAllPlaces() {
        return ResponseEntity.ok(placeService.getAllPlaces());
    }

    @Override
    public ResponseEntity<?> deletePlace(String placeId, String commentToEvent) {
        placeService.deletePlace(placeId, commentToEvent);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<EditPlaceDto> updatePlace(InputPlaceDto inputPlaceDto, String placeId) {
        EditPlaceDto editedPlace = placeService.updatePlace(inputPlaceDto, placeId);
        URI editedPlaceUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(editedPlace.getId())
                .toUri();
        return ResponseEntity.created(editedPlaceUri).body(editedPlace);
    }

    @Override
    public ResponseEntity<List<GetItemDto>> getAllItemsByPlaceId(String placeId) {
        return ResponseEntity.ok(placeService.getAllItemsByPlaceId(placeId));
    }

}
