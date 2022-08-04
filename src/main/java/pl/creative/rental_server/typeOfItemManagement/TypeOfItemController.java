package pl.creative.rental_server.typeOfItemManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;
import pl.creative.rental_server.typeOfItemManagement.dto.FillTypeOfItemDto;
import pl.creative.rental_server.typeOfItemManagement.dto.GetTypeOfItemDto;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TypeOfItemController implements TypeOfItemApi {
    private final TypeOfItemService typeOfItemService;

    @Override
    public ResponseEntity<GetTypeOfItemDto> addTypeOfItem(FillTypeOfItemDto fillTypeOfItemDto) {
        GetTypeOfItemDto savedTypeOfItem = typeOfItemService.addItem(fillTypeOfItemDto);
        URI savedBookUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTypeOfItem.getId())
                .toUri();
        return ResponseEntity.created(savedBookUri).body(savedTypeOfItem);
    }

    @Override
    public GetTypeOfItemDto getTypeOfItemById(String id) {
        return typeOfItemService.getTypeOfItemById(id);
    }

    @Override
    public List<GetTypeOfItemDto> getTypesOfItem() {
        return typeOfItemService.getTypesOfItem();
    }

    @Override
    public ResponseEntity<List<GetItemDto>> getItemsByTypeOfItemId(String id) {
        return ResponseEntity.ok(typeOfItemService.getItemsByTypeOfItemId(id));
    }
}
