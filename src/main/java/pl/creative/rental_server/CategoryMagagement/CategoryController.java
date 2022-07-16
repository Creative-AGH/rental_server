package pl.creative.rental_server.CategoryMagagement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.creative.rental_server.CategoryMagagement.dto.FillCategoryDto;
import pl.creative.rental_server.CategoryMagagement.dto.GetCategoryDto;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryApi{
    private final CategoryService categoryService;
    @Override
    public List<GetCategoryDto> getCategories(){
        return categoryService.getCategories();
    }
    @Override
    public ResponseEntity<GetCategoryDto> addCategory(@RequestBody FillCategoryDto fillCategoryDto) {
        GetCategoryDto savedCategory = categoryService.addCategory(fillCategoryDto);
        URI savedCategoryUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCategory.getId())
                .toUri();
        return ResponseEntity.created(savedCategoryUri).body(savedCategory);
    }
}
