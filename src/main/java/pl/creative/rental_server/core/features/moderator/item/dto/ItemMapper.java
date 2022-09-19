package pl.creative.rental_server.core.features.moderator.item.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.creative.rental_server.db.entities.Item;

@Mapper(componentModel = "spring", uses = ItemMapper.class)
public interface ItemMapper {
    @Mapping(source = "placeId", target = "place", ignore = true)
    @Mapping(source = "images", target = "images", ignore = true)
    Item mapFillItemDtoToItem(FillItemDto fillItemDto);

    @Mapping(source = "placeId", target = "place", ignore = true)
    @Mapping(source = "images", target = "images", ignore = true)
    Item mapReplaceItemDtoToItem(ReplaceItemDto replaceItemDto);


    FillItemDto mapFillItemWithOutPlaceDtoToFillItemDto(FillItemWithOutPlaceDto fillItemWithOutPlaceDto);

    @Mapping(source = "categories", target = "categories")
    GetItemDto mapItemToGetItemDto(Item item);

}
