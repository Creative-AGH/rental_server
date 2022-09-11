package pl.creative.rental_server.core.features.moderator.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.creative.rental_server.core.features.moderator.category.dto.FillCategoryDto;
import pl.creative.rental_server.core.features.moderator.category.dto.GetCategoryDto;
import pl.creative.rental_server.docs.CategoryApi;
import pl.creative.rental_server.core.features.moderator.item.dto.GetItemDto;

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

    @Override // FOR USER
    public ResponseEntity<GetCategoryDto> getCategoryById(String categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @Override // FOR USER
    public ResponseEntity<List<GetCategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Override // FOR USER
    public ResponseEntity<List<GetItemDto>> getAllItemsByCategoryId(String categoryId) {
        return ResponseEntity.ok(categoryService.getAllItemsByCategoryId(categoryId));
    }
}
