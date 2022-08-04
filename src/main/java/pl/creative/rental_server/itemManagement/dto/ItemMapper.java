package pl.creative.rental_server.itemManagement.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.creative.rental_server.entities.Item;

@Mapper(componentModel = "spring", uses = ItemMapper.class)
public interface ItemMapper {
    Item mapFillItemDtoToItem(FillItemDto fillItemDto);

    @Mapping(source = "categories", target = "categories")
    GetItemDto mapItemToGetItemDto(Item item);
}
