package pl.creative.rental_server.placeManagement;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.creative.rental_server.placeManagement.dto.EditPlaceDto;
import pl.creative.rental_server.placeManagement.dto.InputPlaceDto;
import pl.creative.rental_server.placeManagement.dto.PlaceIdDto;
import pl.creative.rental_server.exception.PlaceNotFound;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

public interface PlaceApi {
    @PostMapping("/moderator/place/create")
    @ApiOperation(value = "Creates place and return ID of created Place")
    ResponseEntity<PlaceIdDto> createPlace(@Valid InputPlaceDto dto) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    @ApiOperation(value = "Updates existing place place and return updatedPlace")
    @PostMapping("/moderator/{placeId}/update")
    ResponseEntity<EditPlaceDto> updatePlace(@Valid InputPlaceDto dto, @PathVariable String placeId) throws PlaceNotFound;

    @ApiOperation(value = "Delete place based on given ID")
    @PostMapping("/moderator/{placeId}/delete")
    ResponseEntity<?> deletePlace(@PathVariable String placeId);

}
