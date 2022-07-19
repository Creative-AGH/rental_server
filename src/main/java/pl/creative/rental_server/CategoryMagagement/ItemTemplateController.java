package pl.creative.rental_server.CategoryMagagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.creative.rental_server.CategoryMagagement.dto.FillItemTemplateDto;
import pl.creative.rental_server.CategoryMagagement.dto.GetItemTemplateDto;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemTemplateController implements ItemTemplateApi {
    private final ItemTemplateService itemTemplateService;
    @Override
    public List<GetItemTemplateDto> getCategories(){
        return itemTemplateService.getCategories();
    }
    @Override
    public ResponseEntity<GetItemTemplateDto> addCategory(@RequestBody FillItemTemplateDto fillItemTemplateDto) {
        GetItemTemplateDto savedCategory = itemTemplateService.addCategory(fillItemTemplateDto);
        URI savedCategoryUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCategory.getId())
                .toUri();
        return ResponseEntity.created(savedCategoryUri).body(savedCategory);
    }
}
