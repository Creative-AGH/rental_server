package pl.creative.rental_server.itemManagement.dto;

import lombok.Data;
import org.springframework.lang.Nullable;
import pl.creative.rental_server.entities.StatusOfItem;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class FillItemDto {
    @NotNull(message = "Name can not be null")
    @NotEmpty(message = "Name can not be empty")
    @NotBlank(message = "Name can not be null or empty")
    @Size(max = 255, message = "Name of the item must be shorter than {max} signs")
    private String name;
    @Size(max = 255, message = "Description of the item must be shorter than {max} signs")
    private String description;
    @Nullable
    private List<String> categoriesId;
    //@Enumerated(EnumType.STRING) //it is not necessary because we do that in other way (class StatusOfItemConverter)
    private StatusOfItem statusOfItem;
    private String placeId;
    private List<String> images = new ArrayList<>();

}
