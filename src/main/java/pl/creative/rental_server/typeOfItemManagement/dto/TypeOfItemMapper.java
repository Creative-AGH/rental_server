package pl.creative.rental_server.typeOfItemManagement.dto;

import org.mapstruct.Mapper;
import pl.creative.rental_server.Entities.TypeOfItem;

@Mapper(componentModel = "spring", uses = TypeOfItemMapper.class)
public interface TypeOfItemMapper {
    TypeOfItem mapFillTypeOfItemDtoToTypeOfItem(FillTypeOfItemDto fillTypeOfItemDto);

    GetTypeOfItemDto mapTypeOfItemToGetTypeOfItemDto(TypeOfItem typeOfItem);
}
