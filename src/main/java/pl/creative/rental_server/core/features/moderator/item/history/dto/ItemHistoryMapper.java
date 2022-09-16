package pl.creative.rental_server.core.features.moderator.item.history.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.creative.rental_server.db.entities.ItemHistory;

@Mapper(componentModel = "spring", uses = ItemHistory.class)
public interface ItemHistoryMapper {

    @Mapping(source = "account", target = "account")
    GetItemHistoryDto mapItemHistoryToGetItemHistoryDto(ItemHistory itemHistory);
}
