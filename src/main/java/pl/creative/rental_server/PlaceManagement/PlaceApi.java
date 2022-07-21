package pl.creative.rental_server.PlaceManagement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

public interface PlaceApi {
    @PostMapping("/moderator/{areaId}/create")
    ResponseEntity<?> createPlace(@Valid InputPlaceDto dto, @PathVariable String areaId) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;
    @PostMapping("/moderator/{areaId}/{placeId}/update")
    ResponseEntity<?> updatePlace(@Valid InputPlaceDto dto, @PathVariable String areaId,@PathVariable String placeId);
    @PostMapping("/moderator/{areaId}/{placeId}/delete")
    ResponseEntity<?> deletePlace(@PathVariable String areaId,@PathVariable String placeId);
    @PostMapping("/moderator/{areaId}/{placeId}")
    ResponseEntity<?> getArea(@PathVariable String areaId,@PathVariable String placeId);
}
