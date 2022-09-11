package pl.creative.rental_server.core.features.moderator.category.dto;

import org.mapstruct.Mapper;
import pl.creative.rental_server.db.entities.Category;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface CategoryMapper {
    Category mapFillCategoryDtoToCategory(FillCategoryDto fillCategoryDto);

    GetCategoryDto mapCategoryToGetCategoryDto(Category category);
}
