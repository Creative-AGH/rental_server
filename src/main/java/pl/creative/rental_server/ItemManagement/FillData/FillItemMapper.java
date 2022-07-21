package pl.creative.rental_server.ItemManagement.FillData;

import org.mapstruct.Mapper;
import pl.creative.rental_server.Entities.Item;
import pl.creative.rental_server.ItemManagement.dto.FillItemDto;

@Mapper(componentModel = "spring", uses = FillItemMapper.class)
public interface FillItemMapper {
    Item mapItemDtoToItem(FillItemDto dto);
}
