package pl.creative.rental_server.itemTemplateManagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.itemTemplateManagement.dto.FillItemTemplateDto;
import pl.creative.rental_server.itemTemplateManagement.dto.GetItemTemplateDto;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/itemTemplate")
public interface ItemTemplateApi {
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<GetItemTemplateDto> getItemTemplates();

    @PostMapping
    ResponseEntity<GetItemTemplateDto> addItemTemplate(@RequestBody @Valid FillItemTemplateDto fillItemTemplateDto);

}
