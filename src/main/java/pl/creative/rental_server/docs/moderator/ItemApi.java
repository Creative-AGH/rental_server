package pl.creative.rental_server.docs.moderator;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.core.features.moderator.item.dto.FillItemDto;
import pl.creative.rental_server.core.features.moderator.item.dto.GetItemDto;
import pl.creative.rental_server.core.features.moderator.item.history.dto.GetItemHistoryDto;

import javax.validation.Valid;
import java.util.List;

public interface ItemApi {

    @ApiOperation(value = "Adding new item")
    @PostMapping("/moderator/item/create")
    ResponseEntity<GetItemDto> addItem(@RequestBody @Valid FillItemDto fillItemDto);


    @ApiOperation(value = "Replacing an item")
    @PutMapping("/moderator/item/replace")
    ResponseEntity<GetItemDto> replaceItem(@RequestBody @Valid FillItemDto fillItemDto);

    @ApiOperation(value = "Deleting the item by given item id")
    @DeleteMapping("/moderator/item/delete/{itemId}")
    ResponseEntity<?> deleteItem(@PathVariable String itemId, @RequestBody(required = false) String commentToEvent);

    @ApiOperation(value = "Deleting the item by force (if item is borrowed by someone) by given item id")
    @DeleteMapping("/moderator/item/{itemId}/forceDeleteBorrowedItem")
    ResponseEntity<?> forceDeleteBorrowedItem(@PathVariable String itemId,
                                              @RequestBody(required = false) String commentToEvent,
                                              @RequestParam(required = false, defaultValue = "false") Boolean borrowedItemForceDeleting);


    @ApiOperation(value = "Updating a place of the item")
    @PatchMapping("/moderator/item/{itemId}/{newPlaceId}/changePlaceOfItem")
    ResponseEntity<GetItemDto> updatePlaceOfItem(@PathVariable String itemId, @PathVariable String newPlaceId,
                                                 @RequestBody(required = false) String commentToEvent);

    @ApiOperation(value = "Updating a borrower of the item")
    @PatchMapping("/moderator/item/{itemId}/{newAccountId}/changeBorrowerOfItem")
    ResponseEntity<GetItemDto> updateBorrowerOfItem(@PathVariable String itemId, @PathVariable Long newAccountId,
                                                    @RequestBody(required = false) String commentToEvent);

    @ApiOperation(value = "Getting all history of the item by given item id")
    @GetMapping("/moderator/item/{itemId}/getHistoryOfItem")
    ResponseEntity<List<GetItemHistoryDto>> getAllHistoryOfItem(@PathVariable String itemId);

}
