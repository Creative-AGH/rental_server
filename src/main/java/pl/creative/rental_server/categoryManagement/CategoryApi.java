package pl.creative.rental_server.categoryManagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.categoryManagement.dto.FillCategoryDto;
import pl.creative.rental_server.categoryManagement.dto.GetCategoryDto;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/typeOfItem")
public interface CategoryApi {
    @PostMapping
    ResponseEntity<GetCategoryDto> addCategory(@RequestBody @Valid FillCategoryDto fillCategoryDto);

    @GetMapping("/{id}")
    GetCategoryDto getCategoryById(@PathVariable String id);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<GetCategoryDto> getCategories();

    @GetMapping("/{id}/items")
    ResponseEntity<List<GetItemDto>> getItemsByCategoryId(@PathVariable String id);

}
