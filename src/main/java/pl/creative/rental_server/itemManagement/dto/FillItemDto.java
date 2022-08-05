package pl.creative.rental_server.itemManagement.dto;

import lombok.Data;

import pl.creative.rental_server.entities.StatusOfItem;
import pl.creative.rental_server.placeManagement.dto.InputPlaceDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class FillItemDto {
    //first check if not null, then if not empty, finally check if trimmed string is not blank
    @NotNull(message = "Name can not be null")
    @NotEmpty(message = "Name can not be empty")
    @NotBlank(message = "Name can not be null or empty") //check if trimmed string has more than 0 digits
    @Size(max = 255, message = "Name of the item must be shorter than {max} signs")
    private String name;
    @NotNull(message = "List of category of item id may not be null")
    private List<String> categoriesId;
    //@Enumerated(EnumType.STRING) //it is not necessary because we do that in other way
    private StatusOfItem statusOfItem;
    private String placeId;

}
