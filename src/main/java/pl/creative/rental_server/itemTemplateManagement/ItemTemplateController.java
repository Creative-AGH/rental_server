package pl.creative.rental_server.itemTemplateManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.creative.rental_server.itemTemplateManagement.dto.FillItemTemplateDto;
import pl.creative.rental_server.itemTemplateManagement.dto.GetItemTemplateDto;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemTemplateController implements ItemTemplateApi {
    private final ItemTemplateService itemTemplateService;
    @Override
    public List<GetItemTemplateDto> getItemTemplates(){
        return itemTemplateService.getItemTemplates();
    }
    @Override
    public ResponseEntity<GetItemTemplateDto> addItemTemplate(@RequestBody FillItemTemplateDto fillItemTemplateDto) {
        GetItemTemplateDto savedItemTemplate = itemTemplateService.addItemTemplate(fillItemTemplateDto);
        URI savedCategoryUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedItemTemplate.getId())
                .toUri();
        return ResponseEntity.created(savedCategoryUri).body(savedItemTemplate);
    }
}
