package pl.creative.rental_server.rentItemManagement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RentItemByModeratorApi {

    @PostMapping("/moderator/rentItem/{accountId}/{itemId}")
    ResponseEntity<?> rentItem(@PathVariable Long accountId, @PathVariable String itemId,
                               @RequestBody(required = false) String commentToEvent);

    @PostMapping("/moderator/returnItem/{itemId}")
    ResponseEntity<?> returnItem(@PathVariable String itemId, @RequestBody(required = false) String commentToEvent);
}
