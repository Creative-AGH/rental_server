package pl.creative.rental_server.docs.moderator;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.core.features.moderator.place.dto.EditPlaceDto;
import pl.creative.rental_server.core.features.moderator.place.dto.GetPlaceDto;
import pl.creative.rental_server.core.features.moderator.place.dto.InputPlaceDto;

import javax.validation.Valid;

public interface PlaceApi {

    @ApiOperation(value = "Adding new place")
    @PostMapping("/moderator/place/create")
    ResponseEntity<GetPlaceDto> addPlace(@RequestBody @Valid InputPlaceDto inputPlaceDto);

    @ApiOperation(value = "Deleting the place by given place id")
    @DeleteMapping("/moderator/{placeId}/delete")
    ResponseEntity<?> deletePlace(@PathVariable String placeId, @RequestBody(required = false) String commentToEvent);

    @ApiOperation(value = "Updating the place")
    @PutMapping("/moderator/{placeId}/update")
    ResponseEntity<EditPlaceDto> updatePlace(@RequestBody @Valid InputPlaceDto inputPlaceDto, @PathVariable String placeId);


}
