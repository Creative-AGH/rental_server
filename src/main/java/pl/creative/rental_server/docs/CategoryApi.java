package pl.creative.rental_server.docs;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.core.features.moderator.category.dto.FillCategoryDto;
import pl.creative.rental_server.core.features.moderator.category.dto.GetCategoryDto;
import pl.creative.rental_server.core.features.moderator.item.dto.GetItemDto;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/category")
public interface CategoryApi {

    @ApiOperation(value = "Adding new category")
    @PostMapping
    ResponseEntity<GetCategoryDto> addCategory(@RequestBody @Valid FillCategoryDto fillCategoryDto);

    @ApiOperation(value = "Getting category by given category id")
    @GetMapping("/{categoryId}")
    ResponseEntity<GetCategoryDto> getCategoryById(@PathVariable String categoryId);

    @ApiOperation(value = "Getting all categories")
    @GetMapping()
    ResponseEntity<List<GetCategoryDto>> getAllCategories();

    @ApiOperation(value = "Getting all items by given category id")
    @GetMapping("/{categoryId}/items")
    ResponseEntity<List<GetItemDto>> getAllItemsByCategoryId(@PathVariable String categoryId);

}
