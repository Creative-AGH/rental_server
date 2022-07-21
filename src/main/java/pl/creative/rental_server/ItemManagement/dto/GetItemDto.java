package pl.creative.rental_server.ItemManagement.dto;

import lombok.Data;
import pl.creative.rental_server.CategoryMagagement.dto.GetCategoryDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetItemDto {
    private String id;
    private String name;
    //    private StatusOfItem statusOfItem;
    private LocalDateTime dateOfCreate;
    private List<GetCategoryDto> categories;
}
