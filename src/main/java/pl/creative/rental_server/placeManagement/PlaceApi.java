package pl.creative.rental_server.placeManagement;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;
import pl.creative.rental_server.placeManagement.dto.EditPlaceDto;
import pl.creative.rental_server.placeManagement.dto.GetPlaceDto;
import pl.creative.rental_server.placeManagement.dto.InputPlaceDto;

import javax.validation.Valid;
import java.util.List;

public interface PlaceApi {
    @ApiOperation(value = "Adding new place")
    @PostMapping("/moderator/place/create")
    ResponseEntity<GetPlaceDto> addPlace(@RequestBody @Valid InputPlaceDto inputPlaceDto);

    @ApiOperation(value = "Getting all places")
    @GetMapping("/moderator/places")
    ResponseEntity<List<GetPlaceDto>> getAllPlaces();

    @ApiOperation(value = "Deleting the place by given place id")
    @DeleteMapping("/moderator/{placeId}/delete")
    ResponseEntity<?> deletePlace(@PathVariable String placeId, @RequestBody(required = false) String commentToEvent);

    @ApiOperation(value = "Updating the place")
    @PutMapping("/moderator/{placeId}/update")
    ResponseEntity<EditPlaceDto> updatePlace(@RequestBody @Valid InputPlaceDto inputPlaceDto, @PathVariable String placeId);

    @ApiOperation(value = "Getting all items by given place id")
    @GetMapping("/moderator/{placeId}/getItemsByPlaceId")
    ResponseEntity<List<GetItemDto>> getAllItemsByPlaceId(@PathVariable String placeId);

}
