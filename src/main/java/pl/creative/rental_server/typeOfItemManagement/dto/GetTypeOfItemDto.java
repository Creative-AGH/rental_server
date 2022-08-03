package pl.creative.rental_server.typeOfItemManagement.dto;

import lombok.Data;

@Data
public class GetTypeOfItemDto {
    private String id;
    private String nameOfType;
    private String description;
}
