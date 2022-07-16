package pl.creative.rental_server.ItemManagement.dto;

import lombok.Data;
import pl.creative.rental_server.Entities.StatusOfItem;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
public class FillItemDto {
    @NotBlank(message = "Name may not be empty or null")
    private String name;
//    @Enumerated
//    private StatusOfItem statusOfItem;

}
