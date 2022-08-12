package pl.creative.rental_server.itemManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.creative.rental_server.entities.StatusOfItem;
import pl.creative.rental_server.itemHistoryManagement.dto.GetItemHistoryDto;
import pl.creative.rental_server.itemManagement.dto.FillItemDto;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController implements ItemApi {
    private final ItemService itemService;

    @Override
    public ResponseEntity<GetItemDto> addItem(@RequestBody FillItemDto fillItemDto) {
        GetItemDto savedItem = itemService.addItem(fillItemDto);
        URI savedItemUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedItem.getId())
                .toUri();
        return ResponseEntity.created(savedItemUri).body(savedItem);
    }

    @Override
    public ResponseEntity<GetItemDto> getItemByItemId(String itemId) {
        GetItemDto getItemDto = itemService.getItemByItemId(itemId);
        return ResponseEntity.ok(getItemDto);
    }

    @Override
    public List<GetItemDto> getItems() {
        return itemService.getItems();
    }

    @Override
    public ResponseEntity<GetItemDto> replaceItem(FillItemDto fillItemDto) {
        itemService.replaceItem(fillItemDto);
        return null;
    }

    @Override
    public List<GetItemDto> getItemsByStatusOfItem(StatusOfItem statusOfItem) {
        return itemService.getItemsByStatusOfItem(statusOfItem);
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
    public ResponseEntity<?> deleteItem(String itemId, String commentToEvent) {
        itemService.deleteItem(itemId, commentToEvent);
        return ResponseEntity.noContent().build();
    }

    @Override
    public List<GetItemDto> getBorrowedItems(boolean state) {
        if (state)
            return itemService.getBorrowedItems();
        return itemService.getNotBorrowedItems();
    }

    @Override
    public ResponseEntity<?> updatePlaceOfItem(String itemId, String newPlaceId, String commentToEvent) {
        GetItemDto updatedItem = itemService.updatePlaceOfItem(itemId, newPlaceId, commentToEvent);
        URI updatedItemUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedItem.getId())
                .toUri();
        return ResponseEntity.created(updatedItemUri).body(updatedItem);
    }

    @Override
    public ResponseEntity<GetItemDto> changeBorrowerOfItem(String itemId, Long newAccountId) {
        GetItemDto updatedItem = itemService.changeBorrowerOfItem(itemId, newAccountId);
        URI updatedItemUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedItem.getId())
                .toUri();
        return ResponseEntity.created(updatedItemUri).body(updatedItem);
    }

    @Override
    public List<GetItemHistoryDto> getHistoryOfItem(String itemId) {
        return itemService.getHistoryOfItem(itemId);
    }

}
