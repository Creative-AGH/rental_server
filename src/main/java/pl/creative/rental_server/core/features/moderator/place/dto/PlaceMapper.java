package pl.creative.rental_server.core.features.moderator.place.dto;

import org.mapstruct.Mapper;
import pl.creative.rental_server.db.entities.Place;

@Mapper(componentModel = "spring", uses = PlaceMapper.class)
public interface PlaceMapper {

    Place mapInputPlaceDtoToPlace(InputPlaceDto inputPlaceDto);

    GetPlaceDto mapPlaceToGetPlaceDto(Place place);
}
