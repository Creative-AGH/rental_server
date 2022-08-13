package pl.creative.rental_server.rentItemManagement;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RentItemByModeratorApi {

    @ApiOperation(value = "Renting the item")
    @PostMapping("/moderator/rentItem/{accountId}/{itemId}")
    ResponseEntity<?> rentItem(@PathVariable Long accountId, @PathVariable String itemId,
                               @RequestBody(required = false) String commentToEvent);

    @ApiOperation(value = "Returning the item")
    @PostMapping("/moderator/returnItem/{itemId}")
    ResponseEntity<?> returnItem(@PathVariable String itemId, @RequestBody(required = false) String commentToEvent);
}
