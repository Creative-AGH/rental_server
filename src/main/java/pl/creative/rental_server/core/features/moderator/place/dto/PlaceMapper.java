package pl.creative.rental_server.core.features.moderator.place.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.creative.rental_server.core.global.exception.notFound.PlaceException;
import pl.creative.rental_server.db.entities.Place;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = PlaceMapper.class)
public interface PlaceMapper {
    @Mapping(source = "placeCoordinatesDto", target = "placeCoordinates")
    Place mapInputPlaceDtoToPlace(InputPlaceDto inputPlaceDto);

    default List<Double> mapPointDtoToDouble(List<PointDto> placeCoordinatesDto) {
        List<Double> doubleList = new ArrayList<>();
        if (placeCoordinatesDto.contains(null)) {
            throw new PlaceException("The coordinate can not be null!");
        }
        placeCoordinatesDto.forEach(p -> {
            if (p.getX() == null || p.getY() == null) {
                throw new PlaceException("The value of coordinate can not be null!");
            }
            doubleList.add(p.getX());
            doubleList.add(p.getY());
        });
        return doubleList;
    }

    @Mapping(source = "placeCoordinates", target = "placeCoordinatesDto")
    GetPlaceDto mapPlaceToGetPlaceDto(Place place);

    default List<PointDto> mapDoubleToPointDto(List<Double> placeCoordinates) {
        List<PointDto> pointDtos = new ArrayList<>();
        List<Double> doubleX = new ArrayList<>();
        List<Double> doubleY = new ArrayList<>();
        for (int i = 0; i < placeCoordinates.size(); i++) {
            if (i % 2 == 0) {
                doubleX.add(placeCoordinates.get(i));
            } else {
                doubleY.add(placeCoordinates.get(i));
            }
        }
        for (int i = 0; i < doubleX.size(); i++) {
            pointDtos.add(new PointDto(doubleX.get(i), doubleY.get(i)));
        }
        return pointDtos;
    }

}
