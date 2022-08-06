package pl.creative.rental_server.placeManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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
    public ResponseEntity<GetPlaceDto> addPlace(InputPlaceDto dto) {
        GetPlaceDto savedPlace = placeService.addPlace(dto);
        URI savedPlaceUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPlace.getId())
                .toUri();
        return ResponseEntity.created(savedPlaceUri).body(savedPlace);
    }

    @Override
    public List<GetPlaceDto> getPlaces() {
        return placeService.getPlaces();
    }

    @Override
    public ResponseEntity<?> deletePlace(String placeId) {
        placeService.deletePlace(placeId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<EditPlaceDto> updatePlace(InputPlaceDto dto, String placeId) {
        EditPlaceDto editedPlace = placeService.updatePlace(dto, placeId);
        URI editedPlaceUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(editedPlace.getId())
                .toUri();
        return ResponseEntity.created(editedPlaceUri).body(editedPlace);
    }

}