package pl.creative.rental_server.PlaceManagement;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.creative.rental_server.Entities.Place;
import pl.creative.rental_server.ItemTemplateManagement.dto.ItemTemplateMapper;

@Mapper(componentModel = "spring", uses = PlaceMapper.class)
public interface PlaceMapper {

    Place mapInputPlaceDtoToPlace(InputPlaceDto inputPlaceDto);
}
