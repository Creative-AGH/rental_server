package pl.creative.rental_server.categoryManagement.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class FillCategoryDto {
    //first check if not null, then if not empty, finally check if trimmed string is not blank
    @NotNull(message = "Name can not be null")
    @NotEmpty(message = "Name can not be empty")
    @NotBlank(message = "Name can not be null or empty") //check if trimmed string has more than 0 digits
    @Size(max = 255, message = "Name of the type must be shorter than {max} signs")
    private String categoryName;
    @Size(max = 255, message = "Description of the type must be shorter than {max} signs")
    private String description;
}
