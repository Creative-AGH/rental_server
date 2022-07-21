package pl.creative.rental_server.ItemManagement.dto;

import lombok.Data;
import pl.creative.rental_server.ItemTemplateManagement.dto.GetItemTemplateDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetItemDto {
    private String id;
    private String name;
    //    private StatusOfItem statusOfItem;
    private LocalDateTime dateOfCreate;
    private List<GetItemTemplateDto> categories; //FIXME One item template => For many Items not Many Itemtemplates for Many items
}
