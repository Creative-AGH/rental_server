package pl.creative.rental_server.ItemManagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.ItemManagement.dto.FillItemDto;
import pl.creative.rental_server.ItemManagement.dto.GetItemDto;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/item")
public interface ItemApi {
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<GetItemDto> getItems();

    @PostMapping
    ResponseEntity<GetItemDto> addItem(@Valid @RequestBody FillItemDto fillItemDto);
}
