package pl.creative.rental_server.itemManagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

}
