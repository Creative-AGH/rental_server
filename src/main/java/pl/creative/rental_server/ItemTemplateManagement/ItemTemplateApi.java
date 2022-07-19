package pl.creative.rental_server.ItemTemplateManagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.ItemTemplateManagement.dto.FillItemTemplateDto;
import pl.creative.rental_server.ItemTemplateManagement.dto.GetItemTemplateDto;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/itemTemplate")
public interface ItemTemplateApi {
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<GetItemTemplateDto> getCategories();

    @PostMapping
    ResponseEntity<GetItemTemplateDto> addCategory(@RequestBody @Valid FillItemTemplateDto fillItemTemplateDto);

}
