package pl.creative.rental_server.itemHistoryManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.creative.rental_server.itemHistoryManagement.dto.GetItemHistoryDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemHistoryController implements ItemHistoryApi{
    private final ItemHistoryService itemHistoryService;

    @Override
    public ResponseEntity<?> getAllItemHistory() {
        List<GetItemHistoryDto> listOfGetItemHistoryDto = itemHistoryService.getAllItemHistory();
        return ResponseEntity.ok(listOfGetItemHistoryDto);
    }
}
