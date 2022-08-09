package pl.creative.rental_server.rentHistoryManagement.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.creative.rental_server.entities.RentHistory;

@Mapper(componentModel = "spring", uses = RentHistory.class)
public interface RentHistoryMapper {

    @Mapping(source = "borrowerAccount", target = "borrowerAccount")
//    @Mapping(source = "item", target = "item")
    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.name", target = "itemName")
    @Mapping(source = "item.description", target = "itemDescription")
    GetRentHistoryDto mapRentHistoryToGetRentHistoryDto(RentHistory rentHistory);
}
