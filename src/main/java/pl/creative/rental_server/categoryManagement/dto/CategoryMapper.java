package pl.creative.rental_server.categoryManagement.dto;

import org.mapstruct.Mapper;
import pl.creative.rental_server.entities.Category;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface CategoryMapper {
    Category mapFillCategoryDtoToCategory(FillCategoryDto fillCategoryDto);

    GetCategoryDto mapCategoryToGetCategoryDto(Category category);
}
