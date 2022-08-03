package pl.creative.rental_server.ItemManagement.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.creative.rental_server.Entities.Item;

@Mapper(componentModel = "spring", uses = ItemMapper.class)
public interface ItemMapper {
    Item mapFillItemDtoToItem(FillItemDto fillItemDto);

    @Mapping(source = "typesOfItem", target = "typesOfItem")
    GetItemDto mapItemToGetItemDto(Item item);
}
