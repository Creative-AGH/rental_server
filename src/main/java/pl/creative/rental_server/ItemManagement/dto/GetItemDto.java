package pl.creative.rental_server.ItemManagement.dto;

import lombok.Data;
import pl.creative.rental_server.Entities.StatusOfItem;

import java.time.LocalDateTime;

@Data
public class GetItemDto {
    private String id;
    private String name;
//    private StatusOfItem statusOfItem;
    private LocalDateTime dateOfCreate;
}
