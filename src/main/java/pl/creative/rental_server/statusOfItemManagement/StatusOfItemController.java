package pl.creative.rental_server.statusOfItemManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatusOfItemController implements StatusOfItemApi {
    private final StatusOfItemService statusOfItemService;


    @Override
    public ResponseEntity<?> getAllStatusOfItem() {
        return ResponseEntity.ok(statusOfItemService.getAllStatusOfItem());
    }
}
