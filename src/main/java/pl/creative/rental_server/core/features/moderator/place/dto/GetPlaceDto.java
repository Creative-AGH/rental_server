package pl.creative.rental_server.core.features.moderator.place.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class GetPlaceDto {
    private String id;
    private String name;
    private String description;
    @NotNull
    @Size(min = 4, max = 4, message = "Place has to contain 4 coordinates with (x,y)")
    private List<PointDto> placeCoordinatesDto;
}
