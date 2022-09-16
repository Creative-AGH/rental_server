package pl.creative.rental_server.core.features.user.statusOfItem.dto;

import lombok.Data;
import pl.creative.rental_server.db.entities.StatusOfItem;

@Data
public class GetStatusOfItemDto {
    private final StatusOfItem statusOfItem;
}
