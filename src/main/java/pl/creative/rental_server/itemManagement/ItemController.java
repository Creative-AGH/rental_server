package pl.creative.rental_server.itemManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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
        URI savedBookUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedItem.getId())
                .toUri();
        return ResponseEntity.created(savedBookUri).body(savedItem);
    }

    @Override
    public List<GetItemDto> getItems() {
        return itemService.getItems();
    }


}
