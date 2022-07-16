package pl.creative.rental_server.ItemManagement.dto;

import org.mapstruct.Mapper;
import pl.creative.rental_server.CategoryMagagement.dto.CategoryMapper;
import pl.creative.rental_server.Entities.Item;

@Mapper(componentModel = "spring", uses = ItemMapper.class)
public interface ItemMapper {
    Item mapFillItemDtoToItem(FillItemDto fillItemDto);
    GetItemDto mapItemToGetItemDto(Item item);
}
