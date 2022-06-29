package pl.creative.rental_server.CategoryMagagement.FillData;

import org.mapstruct.Mapper;
import pl.creative.rental_server.Entities.Category;

@Mapper(componentModel="spring", uses=FillCategoryMapper.class)
public interface FillCategoryMapper {

     Category mapCategoryDtoToCategory(FillCategoryDto dto);

}
