package pl.creative.rental_server.categoryManagement.dto;

import lombok.Data;

@Data
public class GetCategoryDto {
    private String id;
    private String categoryName;
    private String description;
}
