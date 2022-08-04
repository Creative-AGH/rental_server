package pl.creative.rental_server.itemManagement.dto;

import lombok.Data;
import pl.creative.rental_server.categoryManagement.dto.GetCategoryDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetItemDto {
    private String id;
    private String name;
    //    private StatusOfItem statusOfItem;
    private LocalDateTime dateOfCreation;
    private List<GetCategoryDto> categories;
    //private List<GetItemTemplateDto> categories; //FIXME One item template => For many Items not Many Itemtemplates for Many items
}
