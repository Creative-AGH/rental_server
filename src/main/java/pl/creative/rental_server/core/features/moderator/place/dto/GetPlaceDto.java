package pl.creative.rental_server.core.features.moderator.place.dto;

import lombok.Data;

@Data
public class GetPlaceDto {
    private String id;
    private String name;
    private String description;
}
