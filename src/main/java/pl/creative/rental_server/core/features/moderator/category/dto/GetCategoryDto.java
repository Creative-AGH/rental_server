package pl.creative.rental_server.core.features.moderator.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCategoryDto {
    private String id;
    private String categoryName;
    private String description;
}
