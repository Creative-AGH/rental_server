package pl.creative.rental_server.itemHistoryManagement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import pl.creative.rental_server.itemHistoryManagement.dto.GetItemHistoryDto;

import java.util.List;

public interface ItemHistoryApi {

    @GetMapping("/moderator/itemHistory")
    ResponseEntity<List<GetItemHistoryDto>> getAllItemHistory();

}
