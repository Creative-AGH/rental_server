package pl.creative.rental_server.categoryManagement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.categoryManagement.dto.FillCategoryDto;
import pl.creative.rental_server.categoryManagement.dto.GetCategoryDto;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/category")
public interface CategoryApi {
    @PostMapping
    ResponseEntity<GetCategoryDto> addCategory(@RequestBody @Valid FillCategoryDto fillCategoryDto);

    @GetMapping("/{categoryId}")
    ResponseEntity<GetCategoryDto> getCategoryById(@PathVariable String categoryId);

    @GetMapping()
    ResponseEntity<List<GetCategoryDto>> getAllCategories();

    @GetMapping("/{categoryId}/items")
    ResponseEntity<List<GetItemDto>> getItemsByCategoryId(@PathVariable String categoryId);

}
