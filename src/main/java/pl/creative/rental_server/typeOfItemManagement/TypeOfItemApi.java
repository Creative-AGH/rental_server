package pl.creative.rental_server.typeOfItemManagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;
import pl.creative.rental_server.typeOfItemManagement.dto.FillTypeOfItemDto;
import pl.creative.rental_server.typeOfItemManagement.dto.GetTypeOfItemDto;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/typeOfItem")
public interface TypeOfItemApi {
    @PostMapping
    ResponseEntity<GetTypeOfItemDto> addTypeOfItem(@RequestBody @Valid FillTypeOfItemDto fillTypeOfItemDto);

    @GetMapping("/{id}")
    GetTypeOfItemDto getTypeOfItemById(@PathVariable String id);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<GetTypeOfItemDto> getTypesOfItem();

    @GetMapping("/{id}/items")
    ResponseEntity<List<GetItemDto>> getItemsByTypeOfItemId(@PathVariable String id);

}
