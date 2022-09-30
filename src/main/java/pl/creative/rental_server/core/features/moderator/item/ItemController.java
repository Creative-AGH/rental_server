package pl.creative.rental_server.core.features.moderator.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.creative.rental_server.core.features.moderator.item.dto.*;
import pl.creative.rental_server.core.features.moderator.item.history.dto.GetItemHistoryDto;
import pl.creative.rental_server.docs.moderator.ItemApi;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController implements ItemApi {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

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
    public ResponseEntity<GetItemDto> addItemToDefaultPlace(@RequestBody FillItemWithOutPlaceDto fillItemWithOutPlaceDto) {
        FillItemDto fillItemDto = itemMapper.mapFillItemWithOutPlaceDtoToFillItemDto(fillItemWithOutPlaceDto);
        fillItemDto.setPlaceId("0");
        GetItemDto savedItem = itemService.addItem(fillItemDto);

        URI savedItemUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedItem.getId())
                .toUri();
        return ResponseEntity.created(savedItemUri).body(savedItem);
    }


    @Override
    public ResponseEntity<GetItemDto> replaceItem(ReplaceItemDto replaceItemDto) {
        return ResponseEntity.ok(itemService.replaceItem(replaceItemDto));
    }


    @Override
    public ResponseEntity<?> deleteItem(String itemId, String commentToEvent) {
        itemService.deleteItem(itemId, commentToEvent, false);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> forceDeleteBorrowedItem(String itemId, String commentToEvent,
                                                     Boolean borrowedItemForceDeleting) {
        itemService.deleteItem(itemId, commentToEvent, borrowedItemForceDeleting);
        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<GetItemDto> updatePlaceOfItem(String itemId, String newPlaceId, String commentToEvent) {
        GetItemDto updatedItem = itemService.updatePlaceOfItem(itemId, newPlaceId, commentToEvent);
        URI updatedItemUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedItem.getId())
                .toUri();
        return ResponseEntity.created(updatedItemUri).body(updatedItem);
    }

    @Override
    public ResponseEntity<GetItemDto> updateBorrowerOfItem(String itemId, Long newAccountId, String commentToEvent) {
        GetItemDto updatedItem = itemService.updateBorrowerOfItem(itemId, newAccountId, commentToEvent);
        URI updatedItemUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedItem.getId())
                .toUri();
        return ResponseEntity.created(updatedItemUri).body(updatedItem);
    }

    @Override
    public ResponseEntity<List<GetItemHistoryDto>> getAllHistoryOfItem(String itemId) {
        return ResponseEntity.ok(itemService.getAllHistoryOfItem(itemId));
    }

}
