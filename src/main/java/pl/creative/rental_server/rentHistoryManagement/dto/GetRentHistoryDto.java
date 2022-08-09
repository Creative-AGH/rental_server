package pl.creative.rental_server.rentHistoryManagement.dto;

import lombok.Data;
import pl.creative.rental_server.accountManagement.dto.GetAccountDto;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;

import java.time.LocalDateTime;

@Data
public class GetRentHistoryDto {
    private Long id;
    private GetAccountDto borrowerAccount;
    private String description;
    private LocalDateTime timeOfRenting;
    private LocalDateTime timeOfReturning;
//    private GetItemDto item;
    private String itemId;
    private String itemName;
    private String itemDescription;
}
