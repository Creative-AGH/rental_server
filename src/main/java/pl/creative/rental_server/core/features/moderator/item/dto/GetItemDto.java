package pl.creative.rental_server.core.features.moderator.item.dto;

import lombok.Data;
import pl.creative.rental_server.core.features.moderator.account.dto.GetAccountDto;
import pl.creative.rental_server.core.features.moderator.category.dto.GetCategoryDto;
import pl.creative.rental_server.db.entities.Image;
import pl.creative.rental_server.db.entities.StatusOfItem;
import pl.creative.rental_server.core.features.moderator.place.dto.GetPlaceDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetItemDto {
    private String id;
    private String name;
    private String description;
    private LocalDateTime dateOfCreation;
    private List<GetCategoryDto> categories;
    private StatusOfItem statusOfItem;
    private GetPlaceDto place;
    private GetAccountDto borrowedBy;
    private List<Image> images;
}
