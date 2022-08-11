package pl.creative.rental_server.itemHistoryManagement.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.creative.rental_server.entities.ItemHistory;

@Mapper(componentModel = "spring", uses = ItemHistory.class)
public interface ItemHistoryMapper {

    @Mapping(source = "account", target = "account")
//    @Mapping(source = "item", target = "item")
    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.name", target = "itemName")
    @Mapping(source = "item.description", target = "itemDescription")
    GetItemHistoryDto mapItemHistoryToGetItemHistoryDto(ItemHistory itemHistory);
}
