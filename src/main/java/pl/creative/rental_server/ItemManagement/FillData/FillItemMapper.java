package pl.creative.rental_server.ItemManagement.FillData;

import org.mapstruct.Mapper;
import pl.creative.rental_server.CategoryMagagement.FillData.FillCategoryMapper;
import pl.creative.rental_server.Entities.Item;

@Mapper(componentModel = "spring", uses = FillCategoryMapper.class)
public interface FillItemMapper {
    Item mapItemDtoToItem(FillItemDto dto);
}
