package pl.creative.rental_server.itemHistoryManagement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface ItemHistoryApi {

    @GetMapping("/moderator/itemHistory")
    ResponseEntity<?> getAllItemHistory();

}
