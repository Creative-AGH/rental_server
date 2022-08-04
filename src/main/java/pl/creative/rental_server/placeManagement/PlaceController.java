package pl.creative.rental_server.placeManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.creative.rental_server.placeManagement.dto.EditPlaceDto;
import pl.creative.rental_server.placeManagement.dto.InputPlaceDto;
import pl.creative.rental_server.placeManagement.dto.PlaceIdDto;
import pl.creative.rental_server.placeManagement.dto.PlaceMapper;
import pl.creative.rental_server.exception.notFound.PlaceNotFound;

@RestController
@RequiredArgsConstructor
public class PlaceController implements PlaceApi {
    private final PlaceService placeService;
    private final PlaceMapper placeMapper;

    public ResponseEntity<PlaceIdDto> createPlace(InputPlaceDto dto) {
        String id = placeService.createPlace(dto);
        return ResponseEntity.ok(new PlaceIdDto(id));
    }

    @Override
    public ResponseEntity<EditPlaceDto> updatePlace(InputPlaceDto dto, String placeId) {

        EditPlaceDto editPlaceDto = placeService.editPlace(placeMapper.mapInputPlaceDtoToInnerPlaceDto(dto, placeId));
        return ResponseEntity.ok(editPlaceDto);
    }

    @Override
    public ResponseEntity<?> deletePlace(String placeId) {
        placeService.deletePlace(placeId);
        return ResponseEntity.noContent().build();
    }

}
