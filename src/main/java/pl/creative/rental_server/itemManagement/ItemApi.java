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

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<GetItemDto> getItems();

    @PutMapping
    ResponseEntity<GetItemDto> replaceItem(@RequestBody @Valid FillItemDto fillItemDto);

    @ApiOperation(value = "Return all items according to given status of item")
    @GetMapping("/moderator/items")
    @ResponseStatus(HttpStatus.OK)
    List<GetItemDto> getItemsByStatusOfItem(@RequestParam StatusOfItem statusOfItem);

    @PutMapping("/moderator/{itemId}")
    ResponseEntity<GetItemDto> updateStatusOfItem(@PathVariable String itemId,
                                                  @RequestParam StatusOfItem newStatusOfItem);

    @DeleteMapping("/{itemId}")
    ResponseEntity<?> deleteItem(@PathVariable String itemId);

    @ApiOperation(value = "Return all borrowed items")
    @GetMapping("/moderator/items/borrowed")
    @ResponseStatus(HttpStatus.OK)
    List<GetItemDto> getBorrowedItems(@RequestParam boolean borrowed);

}
