package pl.creative.rental_server.docs.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.core.features.moderator.item.dto.GetItemDto;
import pl.creative.rental_server.db.entities.StatusOfItem;

import java.util.List;

public interface ItemUserApi {
    @ApiOperation(value = "Getting the item by given item id")
    @GetMapping("/user/item/{itemId}")
    ResponseEntity<GetItemDto> getItemByItemId(@PathVariable String itemId);

    @ApiOperation(value = "Getting all items")
    @GetMapping("/user/items")
    ResponseEntity<List<GetItemDto>> getAllItems();

    @ApiOperation(value = "Getting all items according to given status of the item")
    @GetMapping("/user/items/getItemsByStatusOfItem")
    ResponseEntity<List<GetItemDto>> getAllItemsByStatusOfItem(@RequestParam StatusOfItem statusOfItem);

    @ApiOperation(value = "Updating status of the item")
    @PutMapping("/user/{itemId}")
    ResponseEntity<GetItemDto> updateStatusOfItem(@PathVariable String itemId,
                                                  @RequestParam StatusOfItem newStatusOfItem,
                                                  @RequestBody(required = false) String commentToEvent);

    @ApiOperation(value = "Getting all borrowed items")
    @GetMapping("/user/items/borrowed")
    ResponseEntity<List<GetItemDto>> getBorrowedItems(@RequestParam boolean borrowed);

    @ApiOperation(value = "Getting all items from default place")
    @GetMapping("/user/items/getAllItemsInDefaultPlace")
    ResponseEntity<List<GetItemDto>> getAllItemsInDefaultPlace();
}
