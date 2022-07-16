package pl.creative.rental_server.ItemManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.creative.rental_server.ItemManagement.dto.FillItemDto;
import pl.creative.rental_server.ItemManagement.dto.GetItemDto;

import javax.validation.Valid;
import java.net.URI;

@Controller
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
}
