package pl.creative.rental_server.core.features.moderator.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.creative.rental_server.core.features.moderator.category.dto.FillCategoryDto;
import pl.creative.rental_server.core.features.moderator.category.dto.GetCategoryDto;
import pl.creative.rental_server.docs.moderator.CategoryManageApi;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CategoryManageController implements CategoryManageApi {
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

}
