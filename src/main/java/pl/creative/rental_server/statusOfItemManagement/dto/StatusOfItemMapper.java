package pl.creative.rental_server.statusOfItemManagement.dto;

import org.mapstruct.Mapper;
import pl.creative.rental_server.entities.StatusOfItem;

@Mapper(componentModel = "spring", uses = StatusOfItem.class)
public interface StatusOfItemMapper {
    GetStatusOfItemDto mapStatusOfItemToGetStatusOfItemDto(StatusOfItem statusOfItem);
}
