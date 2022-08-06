package pl.creative.rental_server.placeManagement;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.placeManagement.dto.GetPlaceDto;
import pl.creative.rental_server.placeManagement.dto.InputPlaceDto;

import javax.validation.Valid;
import java.util.List;

public interface PlaceApi {
    @PostMapping("/moderator/place/create")
    @ApiOperation(value = "Creates place and return ID of created Place")
    ResponseEntity<GetPlaceDto> addPlace(@RequestBody @Valid InputPlaceDto dto);

    @GetMapping("/moderator/places")
    @ResponseStatus(HttpStatus.OK)
    List<GetPlaceDto> getPlaces();

    @ApiOperation(value = "Deletes place based on given ID")
    @DeleteMapping("/moderator/{placeId}/delete")
    ResponseEntity<?> deletePlace(@PathVariable String placeId);

    @ApiOperation(value = "Updates existing place and return updatedPlace")
    @PutMapping("/moderator/{placeId}/update")
    ResponseEntity<?> updatePlace(@RequestBody @Valid InputPlaceDto dto, @PathVariable String placeId);

}