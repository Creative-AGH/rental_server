package pl.creative.rental_server.itemManagement.dto;

import lombok.Data;
import pl.creative.rental_server.categoryManagement.dto.GetCategoryDto;
import pl.creative.rental_server.entities.StatusOfItem;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetItemDto {
    private String id;
    private String name;
    private LocalDateTime dateOfCreation;
    private List<GetCategoryDto> categories;
    private StatusOfItem statusOfItem;
    //private List<GetItemTemplateDto> categories; //FIXME One item template => For many Items not Many Itemtemplates for Many items
}
