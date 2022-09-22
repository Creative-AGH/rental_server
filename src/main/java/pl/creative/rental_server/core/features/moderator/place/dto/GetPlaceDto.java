package pl.creative.rental_server.core.features.moderator.place.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetPlaceDto {
    private String id;
    private String name;
    private String description;
    private List<PointDto> placeCoordinatesDto;
}
