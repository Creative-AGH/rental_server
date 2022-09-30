package pl.creative.rental_server.core.features.moderator.item.history.dto;

import lombok.Data;
import pl.creative.rental_server.core.features.moderator.account.dto.GetAccountDto;

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
