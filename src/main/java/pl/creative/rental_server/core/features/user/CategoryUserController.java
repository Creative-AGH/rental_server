package pl.creative.rental_server.core.features.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.creative.rental_server.core.features.moderator.category.CategoryService;
import pl.creative.rental_server.core.features.moderator.category.dto.GetCategoryDto;
import pl.creative.rental_server.core.features.moderator.item.dto.GetItemDto;
import pl.creative.rental_server.docs.user.CategoryUserApi;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryUserController implements CategoryUserApi {
    private final CategoryService categoryService;

    @Override
    public ResponseEntity<GetCategoryDto> getCategoryById(String categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @Override
    public ResponseEntity<List<GetCategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Override
    public ResponseEntity<List<GetItemDto>> getAllItemsByCategoryId(String categoryId) {
        return ResponseEntity.ok(categoryService.getAllItemsByCategoryId(categoryId));
    }
}
