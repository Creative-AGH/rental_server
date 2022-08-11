package pl.creative.rental_server.itemHistoryManagement.dto;

import lombok.Data;
import pl.creative.rental_server.accountManagement.dto.GetAccountDto;

import java.time.LocalDateTime;

@Data
public class GetItemHistoryDto {
    private Long id;
    private GetAccountDto account;
    private String typeOfEvent;
    private String commentToEvent;
    private LocalDateTime timeOfEvent;
//    private GetItemDto item;
//    private String itemName;
//    private String itemDescription;
//    private String detailsOfItemBeforeEvent;
}
