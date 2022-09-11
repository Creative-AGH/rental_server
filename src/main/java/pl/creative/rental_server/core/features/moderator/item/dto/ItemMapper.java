package pl.creative.rental_server.core.features.moderator.item.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.creative.rental_server.db.entities.Item;

@Mapper(componentModel = "spring", uses = ItemMapper.class)
public interface ItemMapper {
    @Mapping(source = "placeId", target = "id")
    @Mapping(source = "images", target = "images", ignore = true)
    Item mapFillItemDtoToItem(FillItemDto fillItemDto);

    @Mapping(source = "categories", target = "categories")
    GetItemDto mapItemToGetItemDto(Item item);
}
