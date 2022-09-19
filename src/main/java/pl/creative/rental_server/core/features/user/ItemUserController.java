package pl.creative.rental_server.core.features.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.creative.rental_server.core.features.moderator.item.ItemService;
import pl.creative.rental_server.core.features.moderator.item.dto.GetItemDto;
import pl.creative.rental_server.db.entities.StatusOfItem;
import pl.creative.rental_server.docs.user.ItemUserApi;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemUserController implements ItemUserApi {
    private final ItemService itemService;

    @Override
    public ResponseEntity<GetItemDto> getItemByItemId(String itemId) {
        GetItemDto getItemDto = itemService.getItemByItemId(itemId);
        return ResponseEntity.ok(getItemDto);
    }

    @Override
    public ResponseEntity<List<GetItemDto>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @Override
    public ResponseEntity<List<GetItemDto>> getAllItemsByStatusOfItem(StatusOfItem statusOfItem) {
        return ResponseEntity.ok(itemService.getAllItemsByStatusOfItem(statusOfItem));
    }

    @Override
    public ResponseEntity<GetItemDto> updateStatusOfItem(String itemId, StatusOfItem newStatusOfItem, String commentToEvent) {
        GetItemDto updatedItem = itemService.updateStatusOfItem(itemId, newStatusOfItem, commentToEvent);
        URI updatedItemUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedItem.getId())
                .toUri();
        return ResponseEntity.created(updatedItemUri).body(updatedItem);
    }

    @Override
    public ResponseEntity<List<GetItemDto>> getBorrowedItems(boolean state) {
        if (state)
            return ResponseEntity.ok(itemService.getBorrowedItems());
        return ResponseEntity.ok(itemService.getNotBorrowedItems());
    }

    @Override
    public ResponseEntity<List<GetItemDto>> getAllItemsFromDefaultPlace() {
        return ResponseEntity.ok(itemService.getAllItemsFromDefaultPlace());
    }
}
