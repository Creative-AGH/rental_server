package pl.creative.rental_server.itemManagement.fillData;

import org.mapstruct.Mapper;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.itemManagement.dto.FillItemDto;

@Mapper(componentModel = "spring", uses = FillItemMapper.class)
public interface FillItemMapper {
    Item mapItemDtoToItem(FillItemDto dto);
}
