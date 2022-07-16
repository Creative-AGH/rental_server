package pl.creative.rental_server.ItemManagement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.creative.rental_server.ItemManagement.dto.FillItemDto;
import pl.creative.rental_server.ItemManagement.dto.GetItemDto;

import javax.validation.Valid;

@RequestMapping("/item")
public interface ItemApi {

    @PostMapping
    ResponseEntity<GetItemDto> addItem(@Valid @RequestBody FillItemDto fillItemDto);
}
