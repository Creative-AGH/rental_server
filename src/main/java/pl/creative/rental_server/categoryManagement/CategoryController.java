package pl.creative.rental_server.categoryManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.creative.rental_server.categoryManagement.dto.FillCategoryDto;
import pl.creative.rental_server.categoryManagement.dto.GetCategoryDto;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryApi {
    private final CategoryService categoryService;

    @Override
    public ResponseEntity<GetCategoryDto> addCategory(FillCategoryDto fillCategoryDto) {
        GetCategoryDto savedCategory = categoryService.addItem(fillCategoryDto);
        URI savedBookUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCategory.getId())
                .toUri();
        return ResponseEntity.created(savedBookUri).body(savedCategory);
    }

    @Override
    public GetCategoryDto getCategoryById(String id) {
        return categoryService.getCategoryById(id);
    }

    @Override
    public List<GetCategoryDto> getCategories() {
        return categoryService.getTypesOfItem();
    }

    @Override
    public ResponseEntity<List<GetItemDto>> getItemsByCategoryId(String id) {
        return ResponseEntity.ok(categoryService.getItemsByCategoryId(id));
    }
}
