package pl.creative.rental_server.itemManagement;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.entities.StatusOfItem;
import pl.creative.rental_server.itemManagement.dto.FillItemDto;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/item")
public interface ItemApi {

    @PostMapping
    ResponseEntity<GetItemDto> addItem(@RequestBody @Valid FillItemDto fillItemDto);

    @GetMapping("/{itemId}")
    ResponseEntity<GetItemDto> getItemByItemId(@PathVariable String itemId);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<GetItemDto> getItems();

    @PutMapping
    ResponseEntity<GetItemDto> replaceItem(@RequestBody @Valid FillItemDto fillItemDto);

    @ApiOperation(value = "Return all items according to given status of item")
    @GetMapping("/moderator/items/getItemsByStatusOfItem")
    @ResponseStatus(HttpStatus.OK)
    List<GetItemDto> getItemsByStatusOfItem(@RequestParam StatusOfItem statusOfItem); ///to user

    @PutMapping("/moderator/{itemId}")
///to user
    ResponseEntity<GetItemDto> updateStatusOfItem(@PathVariable String itemId,
                                                  @RequestParam StatusOfItem newStatusOfItem,
                                                  @RequestBody(required = false) String commentToEvent);

    @DeleteMapping("/{itemId}")
    ResponseEntity<?> deleteItem(@PathVariable String itemId, @RequestBody(required = false) String commentToEvent);

    @ApiOperation(value = "Return all borrowed items")
    @GetMapping("/moderator/items/borrowed")
    @ResponseStatus(HttpStatus.OK)
    List<GetItemDto> getBorrowedItems(@RequestParam boolean borrowed);

    @PatchMapping("/moderator/{itemId}/{newPlaceId}/changePlaceOfItem")
    ResponseEntity<?> updatePlaceOfItem(@PathVariable String itemId, @PathVariable String newPlaceId,
                                        @RequestBody(required = false) String commentToEvent);

    @PatchMapping("/moderator/{itemId}/{newAccountId}/changeBorrowerOfItem")
    ResponseEntity<?> changeBorrowerOfItem(@PathVariable String itemId, @PathVariable Long newAccountId);

}
