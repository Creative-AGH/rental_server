package pl.creative.rental_server.core.features.moderator.statusOfItem.dto;

import org.mapstruct.Mapper;
import pl.creative.rental_server.db.entities.StatusOfItem;

@Mapper(componentModel = "spring", uses = StatusOfItem.class)
public interface StatusOfItemMapper {
    GetStatusOfItemDto mapStatusOfItemToGetStatusOfItemDto(StatusOfItem statusOfItem);
}
