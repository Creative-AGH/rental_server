package pl.creative.rental_server.CategoryMagagement.dto;

import org.mapstruct.Mapper;
import pl.creative.rental_server.Entities.Category;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface CategoryMapper {

    Category mapFillCategoryDtoToCategory(FillCategoryDto fillCategoryDto);

    GetCategoryDto mapCategoryToGetCategoryDto(Category category);

}
