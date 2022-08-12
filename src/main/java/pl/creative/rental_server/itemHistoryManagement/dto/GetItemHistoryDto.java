package pl.creative.rental_server.itemHistoryManagement.dto;

import lombok.Data;
import pl.creative.rental_server.accountManagement.dto.GetAccountDto;

import java.time.LocalDateTime;

@Data
public class GetItemHistoryDto {
    private Long id;
    private String typeOfEvent;
    private String commentToEvent;
    private LocalDateTime timeOfEvent;
    private String itemId;
    private GetAccountDto account;
    private String detailsOfItemBeforeEvent;
}
