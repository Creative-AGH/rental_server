package pl.creative.rental_server.rentItemManagement;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RentItemByModeratorController implements RentItemByModeratorApi {
    private final RentItemByModeratorService rentItemByModeratorService;

    @Override
    public ResponseEntity<?> rentItem(Long accountId, String itemId, String commentToEvent) {
        rentItemByModeratorService.rentItem(accountId, itemId, commentToEvent);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> returnItem(String itemId, String commentToEvent) {
        rentItemByModeratorService.returnItem(itemId, commentToEvent);
        return ResponseEntity.ok().build();
    }
}
