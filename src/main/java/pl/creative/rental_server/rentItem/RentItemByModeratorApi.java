package pl.creative.rental_server.rentItem;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface RentItemByModeratorApi {

    @PostMapping("/moderator/rentItem/{accountId}/{itemId}")
    ResponseEntity<?> rentItem(@PathVariable Long accountId, @PathVariable String itemId);

    @PostMapping("/moderator/returnItem/{itemId}")
    ResponseEntity<?> returnItem(@PathVariable String itemId);
}
