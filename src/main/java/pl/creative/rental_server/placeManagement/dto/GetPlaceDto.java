package pl.creative.rental_server.placeManagement.dto;

import lombok.Data;

@Data
public class GetPlaceDto {
    private String id;
    private String name;
    private String description;
}
