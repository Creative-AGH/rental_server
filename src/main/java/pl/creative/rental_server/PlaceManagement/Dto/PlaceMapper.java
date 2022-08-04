package pl.creative.rental_server.PlaceManagement.Dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.creative.rental_server.Entities.Place;

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
