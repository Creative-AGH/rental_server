package pl.creative.rental_server.placeManagement.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.creative.rental_server.entities.Place;

@Mapper(componentModel = "spring", uses = PlaceMapper.class)
public interface PlaceMapper {

    Place mapInputPlaceDtoToPlace(InputPlaceDto inputPlaceDto);

    InnerPlaceDto mapInputPlaceDtoToInnerPlaceDto(InputPlaceDto inputPlaceDto, String placeId);

    @Mapping(source = "placeId", target = "id")
    @Mapping(source = "inputPlaceDto.description", target = "description")
    @Mapping(source = "inputPlaceDto.name", target = "name")
    Place mapInnerPlaceDtoToPlace(InnerPlaceDto dto);

    EditPlaceDto mapPlaceToEditPlaceDto(Place editPlaceDto);
}
