package pl.creative.rental_server.ItemManagement.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class FillItemDto {
    @NotBlank(message = "Name may not be empty or null")
    private String name;
    @NotNull
    private List<String> categoryIds;
//    @Enumerated
//    private StatusOfItem statusOfItem;

}
