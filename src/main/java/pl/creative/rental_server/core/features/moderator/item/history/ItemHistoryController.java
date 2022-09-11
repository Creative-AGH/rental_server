package pl.creative.rental_server.core.features.moderator.item.history;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.creative.rental_server.docs.ItemHistoryApi;
import pl.creative.rental_server.core.features.moderator.item.history.dto.GetItemHistoryDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemHistoryController implements ItemHistoryApi {
    private final ItemHistoryService itemHistoryService;

    @Override
    public ResponseEntity<List<GetItemHistoryDto>> getAllItemHistory() {
        List<GetItemHistoryDto> listOfGetItemHistoryDto = itemHistoryService.getAllItemHistory();
        return ResponseEntity.ok(listOfGetItemHistoryDto);
    }
}
