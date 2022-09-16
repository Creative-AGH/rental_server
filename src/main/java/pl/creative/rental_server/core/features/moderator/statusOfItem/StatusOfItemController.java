package pl.creative.rental_server.core.features.moderator.statusOfItem;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.creative.rental_server.docs.StatusOfItemApi;

@RestController
@RequiredArgsConstructor
public class StatusOfItemController implements StatusOfItemApi {
    private final StatusOfItemService statusOfItemService;


    @Override//FOR USER
    public ResponseEntity<?> getAllStatusOfItem() {
        return ResponseEntity.ok(statusOfItemService.getAllStatusOfItem());
    }
}
