package pl.creative.rental_server.CategoryMagagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.creative.rental_server.CategoryMagagement.dto.FillCategoryDto;
import pl.creative.rental_server.CategoryMagagement.dto.GetCategoryDto;

import java.util.List;

@RequestMapping("/category")
public interface CategoryApi {
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<GetCategoryDto> getCategories();

    @PostMapping
    ResponseEntity<GetCategoryDto> addCategory(@RequestBody FillCategoryDto fillCategoryDto);

}
