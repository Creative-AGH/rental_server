package pl.creative.rental_server.placeManagement.dto;

import lombok.Data;

@Data
public class EditPlaceDto {
    private final String id;
    private final String name;
    private final String description;
}
