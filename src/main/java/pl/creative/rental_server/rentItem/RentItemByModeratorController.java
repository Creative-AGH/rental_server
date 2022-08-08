package pl.creative.rental_server.rentItem;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RentItemByModeratorController implements RentItemByModeratorApi {
    private final RentItemByModeratorService rentItemByModeratorService;

    @Override
    public ResponseEntity<?> rentItem(Long accountId, String itemId) {
        rentItemByModeratorService.rentItem(accountId, itemId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> returnItem(String itemId) {
        rentItemByModeratorService.returnItem(itemId);
        return ResponseEntity.ok().build();
    }
}
