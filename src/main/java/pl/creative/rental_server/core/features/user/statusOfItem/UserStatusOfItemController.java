package pl.creative.rental_server.core.features.user.statusOfItem;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.creative.rental_server.docs.user.UserStatusOfItemApi;

@RestController
@RequiredArgsConstructor
public class UserStatusOfItemController implements UserStatusOfItemApi {
    private final StatusOfItemService statusOfItemService;


    @Override
    public ResponseEntity<?> getAllStatusOfItem() {
        return ResponseEntity.ok(statusOfItemService.getAllStatusOfItem());
    }
}
