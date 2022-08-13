package pl.creative.rental_server.itemManagement;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.entities.StatusOfItem;
import pl.creative.rental_server.itemHistoryManagement.dto.GetItemHistoryDto;
import pl.creative.rental_server.itemManagement.dto.FillItemDto;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/item")
public interface ItemApi {

    @ApiOperation(value = "Adding new item")
    @PostMapping
    ResponseEntity<GetItemDto> addItem(@RequestBody @Valid FillItemDto fillItemDto);

    @ApiOperation(value = "Getting the item by given item id")
    @GetMapping("/{itemId}")
    ResponseEntity<GetItemDto> getItemByItemId(@PathVariable String itemId);

    @ApiOperation(value = "Getting all items")
    @GetMapping()
    ResponseEntity<List<GetItemDto>> getAllItems();

    @ApiOperation(value = "Replacing an item")
    @PutMapping
    ResponseEntity<GetItemDto> replaceItem(@RequestBody @Valid FillItemDto fillItemDto);

    @ApiOperation(value = "Getting all items according to given status of the item")
    @GetMapping("/moderator/items/getItemsByStatusOfItem")
    ResponseEntity<List<GetItemDto>> getAllItemsByStatusOfItem(@RequestParam StatusOfItem statusOfItem);

    @ApiOperation(value = "Updating status of the item")
    @PutMapping("/moderator/{itemId}")
    ResponseEntity<GetItemDto> updateStatusOfItem(@PathVariable String itemId,
                                                  @RequestParam StatusOfItem newStatusOfItem,
                                                  @RequestBody(required = false) String commentToEvent);

    @ApiOperation(value = "Deleting the item by given item id")
    @DeleteMapping("/{itemId}")
    ResponseEntity<?> deleteItem(@PathVariable String itemId, @RequestBody(required = false) String commentToEvent);

    @ApiOperation(value = "Deleting the item by force (if item is borrowed by someone) by given item id")
    @DeleteMapping("/moderator/{itemId}/forceDeleteBorrowedItem")
    ResponseEntity<?> forceDeleteBorrowedItem(@PathVariable String itemId,
                                              @RequestBody(required = false) String commentToEvent,
                                              @RequestParam(required = false, defaultValue = "false") Boolean borrowedItemForceDeleting);

    @ApiOperation(value = "Getting all borrowed items")
    @GetMapping("/moderator/items/borrowed")
    ResponseEntity<List<GetItemDto>> getBorrowedItems(@RequestParam boolean borrowed);

    @ApiOperation(value = "Updating a place of the item")
    @PatchMapping("/moderator/{itemId}/{newPlaceId}/changePlaceOfItem")
    ResponseEntity<GetItemDto> updatePlaceOfItem(@PathVariable String itemId, @PathVariable String newPlaceId,
                                                 @RequestBody(required = false) String commentToEvent);

    @ApiOperation(value = "Updating a borrower of the item")
    @PatchMapping("/moderator/{itemId}/{newAccountId}/changeBorrowerOfItem")
    ResponseEntity<GetItemDto> updateBorrowerOfItem(@PathVariable String itemId, @PathVariable Long newAccountId,
                                                    @RequestBody(required = false) String commentToEvent);

    @ApiOperation(value = "Getting all history of the item by given item id")
    @GetMapping("/moderator/{itemId}/getHistoryOfItem")
    ResponseEntity<List<GetItemHistoryDto>> getAllHistoryOfItem(@PathVariable String itemId);

}
