package pl.creative.rental_server.PlaceManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.creative.rental_server.PlaceManagement.Dto.EditPlaceDto;
import pl.creative.rental_server.PlaceManagement.Dto.InputPlaceDto;
import pl.creative.rental_server.PlaceManagement.Dto.PlaceIdDto;
import pl.creative.rental_server.PlaceManagement.Dto.PlaceMapper;
import pl.creative.rental_server.exception.PlaceNotFound;

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
    public ResponseEntity<EditPlaceDto> updatePlace(InputPlaceDto dto, String placeId) throws PlaceNotFound {

        EditPlaceDto editPlaceDto = placeService.editPlace(placeMapper.mapInputPlaceDtoToInnerPlaceDto(dto, placeId));
        return ResponseEntity.ok(editPlaceDto);
    }

    @Override
    public ResponseEntity<?> deletePlace(String placeId) {
        placeService.deletePlace(placeId);
        return ResponseEntity.noContent().build();
    }

}
