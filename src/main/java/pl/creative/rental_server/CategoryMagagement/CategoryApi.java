package pl.creative.rental_server.CategoryMagagement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.creative.rental_server.CategoryMagagement.dto.FillCategoryDto;
import pl.creative.rental_server.CategoryMagagement.dto.GetCategoryDto;

@RequestMapping("/category")
public interface CategoryApi {
    @PostMapping
    ResponseEntity<GetCategoryDto> addCategory(@RequestBody FillCategoryDto fillCategoryDto);

}
