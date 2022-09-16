package pl.creative.rental_server.docs.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.creative.rental_server.core.features.moderator.item.dto.GetItemDto;
import pl.creative.rental_server.core.features.moderator.place.dto.GetPlaceDto;

import java.util.List;

public interface PlaceUserApi {


    @ApiOperation(value = "Getting all items by given place id")
    @GetMapping("/user/{placeId}/getItemsByPlaceId")
    ResponseEntity<List<GetItemDto>> getAllItemsByPlaceId(@PathVariable String placeId);

    @ApiOperation(value = "Getting all places")
    @GetMapping("/user/places")
    ResponseEntity<List<GetPlaceDto>> getAllPlaces();

    @ApiOperation(value = "Getting the place by given place id")
    @GetMapping("/user/place/{placeId}")
    ResponseEntity<GetPlaceDto> getPlaceById(@PathVariable String placeId);
}
