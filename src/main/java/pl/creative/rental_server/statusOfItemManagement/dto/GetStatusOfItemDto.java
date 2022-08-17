package pl.creative.rental_server.statusOfItemManagement.dto;

import lombok.Data;
import pl.creative.rental_server.entities.StatusOfItem;

@Data
public class GetStatusOfItemDto {
    private final StatusOfItem statusOfItem;
}
