package pl.creative.rental_server.CategoryMagagement.dto;

import lombok.Data;

@Data
public class GetCategoryDto {
    private String id;
    private String name;
    private String description;
}
