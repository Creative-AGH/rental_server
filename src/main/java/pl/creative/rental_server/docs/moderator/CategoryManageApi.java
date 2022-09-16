package pl.creative.rental_server.docs.moderator;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.creative.rental_server.core.features.moderator.category.dto.FillCategoryDto;
import pl.creative.rental_server.core.features.moderator.category.dto.GetCategoryDto;

import javax.validation.Valid;


public interface CategoryManageApi {

    @ApiOperation(value = "Adding new category")
    @PostMapping("/moderator/category/add")
    ResponseEntity<GetCategoryDto> addCategory(@RequestBody @Valid FillCategoryDto fillCategoryDto);


}
