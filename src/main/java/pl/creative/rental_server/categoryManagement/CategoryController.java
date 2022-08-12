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
        GetCategoryDto savedCategory = categoryService.addCategory(fillCategoryDto);
        URI savedBookUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCategory.getId())
                .toUri();
        return ResponseEntity.created(savedBookUri).body(savedCategory);
    }

    @Override
    public ResponseEntity<GetCategoryDto> getCategoryById(String categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @Override
    public ResponseEntity<List<GetCategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Override
    public ResponseEntity<List<GetItemDto>> getItemsByCategoryId(String categoryId) {
        return ResponseEntity.ok(categoryService.getItemsByCategoryId(categoryId));
    }
}
