package pl.creative.rental_server.core.features.moderator.category.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class FillCategoryDto {
    @NotNull(message = "Category name can not be null")
    @NotEmpty(message = "Category name can not be empty")
    @NotBlank(message = "Category name can not be null or empty")
    @Size(max = 255, message = "Category name must be shorter than {max} signs")
    private String categoryName;
    @Size(max = 255, message = "Description of the category must be shorter than {max} signs")
    private String description;
}
