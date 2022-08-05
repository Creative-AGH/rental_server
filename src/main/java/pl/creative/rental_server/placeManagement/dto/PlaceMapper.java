package pl.creative.rental_server.placeManagement.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.creative.rental_server.entities.Place;

@Mapper(componentModel = "spring", uses = PlaceMapper.class)
public interface PlaceMapper {

    Place mapInputPlaceDtoToPlace(InputPlaceDto inputPlaceDto);

    GetPlaceDto mapPlaceToGetPlaceDto(Place place);

    InnerPlaceDto mapInputPlaceDtoToInnerPlaceDto(InputPlaceDto inputPlaceDto, String placeId);

    @Mapping(source = "placeId", target = "id")
    @Mapping(source = "inputPlaceDto.description", target = "description") //InnerPlaceDto has inside inputPlaceDto
    @Mapping(source = "inputPlaceDto.name", target = "name")
    Place mapInnerPlaceDtoToPlace(InnerPlaceDto innerPlaceDto);

    EditPlaceDto mapPlaceToEditPlaceDto(Place place);
}
