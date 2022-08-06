package pl.creative.rental_server.placeManagement.dto;

import org.mapstruct.Mapper;
import pl.creative.rental_server.entities.Place;

@Mapper(componentModel = "spring", uses = PlaceMapper.class)
public interface PlaceMapper {

    Place mapInputPlaceDtoToPlace(InputPlaceDto inputPlaceDto);

    GetPlaceDto mapPlaceToGetPlaceDto(Place place);
}
