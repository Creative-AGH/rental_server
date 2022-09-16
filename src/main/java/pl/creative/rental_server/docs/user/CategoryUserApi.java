package pl.creative.rental_server.docs.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.creative.rental_server.core.features.moderator.category.dto.GetCategoryDto;
import pl.creative.rental_server.core.features.moderator.item.dto.GetItemDto;

import java.util.List;

public interface CategoryUserApi {
    @ApiOperation(value = "Getting category by given category id")
    @GetMapping("/user/category/{categoryId}")
    ResponseEntity<GetCategoryDto> getCategoryById(@PathVariable String categoryId);

    @ApiOperation(value = "Getting all categories")
    @GetMapping("/user/category/all")//FORUSER
    ResponseEntity<List<GetCategoryDto>> getAllCategories();

    @ApiOperation(value = "Getting all items by given category id")
    @GetMapping("user/category/{categoryId}/items")
    ResponseEntity<List<GetItemDto>> getAllItemsByCategoryId(@PathVariable String categoryId);
}
