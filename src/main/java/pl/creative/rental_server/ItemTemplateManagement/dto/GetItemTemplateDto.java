package pl.creative.rental_server.ItemTemplateManagement.dto;

import lombok.Data;

@Data
public class GetItemTemplateDto {
    private String id;
    private String name;
    private String description;
}
