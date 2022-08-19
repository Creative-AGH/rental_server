package pl.creative.rental_server.placeManagement.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class InputPlaceDto {
    @NotNull(message = "Name can not be null")
    @NotEmpty(message = "Name can not be empty")
    @NotBlank(message = "Name can not be null or empty")
    @Size(max = 255, message = "Name of the place must be shorter than {max} signs")
    private final String name;
    @Size(max = 255, message = "Description of the place must be shorter than {max} signs")
    private final String description;
}
