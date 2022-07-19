package pl.creative.rental_server.CategoryMagagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.CategoryMagagement.dto.FillItemTemplateDto;
import pl.creative.rental_server.CategoryMagagement.dto.GetItemTemplateDto;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/category")
public interface ItemTemplateApi {
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<GetItemTemplateDto> getCategories();

    @PostMapping
    ResponseEntity<GetItemTemplateDto> addCategory(@RequestBody @Valid FillItemTemplateDto fillItemTemplateDto);

}
