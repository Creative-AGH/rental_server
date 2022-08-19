package pl.creative.rental_server.itemManagement.dto;

import lombok.Data;
import pl.creative.rental_server.accountManagement.dto.GetAccountDto;
import pl.creative.rental_server.categoryManagement.dto.GetCategoryDto;
import pl.creative.rental_server.entities.Image;
import pl.creative.rental_server.entities.StatusOfItem;
import pl.creative.rental_server.placeManagement.dto.GetPlaceDto;

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
