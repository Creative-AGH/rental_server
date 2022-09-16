package pl.creative.rental_server.core.features.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.docs.user.RentUserApi;

@Slf4j
@RestController
@AllArgsConstructor
public class RentUserController implements RentUserApi {

    private final RentUserService rentUserService;

    @Override
    public ResponseEntity<?> rentItemForMe(String itemId, String commentToEvent) {
        rentUserService.rentItemForMe(itemId, commentToEvent);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> iReturnItem(String itemId, String commentToEvent) {
        rentUserService.iReturnItem(itemId, commentToEvent);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> iReturnItemAndPlaceItAtGivenPlace(String itemId, String newPlaceId, String commentToEvent) {
        rentUserService.iReturnItemAndPlaceItAtGivenPlace(itemId, newPlaceId, commentToEvent);
        return ResponseEntity.ok().build();
    }

}
