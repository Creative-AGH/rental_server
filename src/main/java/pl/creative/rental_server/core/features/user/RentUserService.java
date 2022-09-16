package pl.creative.rental_server.core.features.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.core.features.moderator.item.ItemService;
import pl.creative.rental_server.core.features.moderator.rent.RentItemService;
import pl.creative.rental_server.db.entities.Account;
import pl.creative.rental_server.db.repository.AccountRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RentUserService {
    private final AccountRepository accountRepository;
    private final RentItemService rentItemService;
    private final ItemService itemService;

    public void rentItemForMe(String itemId, String commentToEvent) {
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Renting an item with id {} by user account email: {}", itemId,accountEmail);
        Optional<Account> optionalAccount = accountRepository.findByEmail(accountEmail);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            Long accountId = account.getId();
            rentItemService.rentItem(accountId, itemId, commentToEvent);
        }
    }

    public void iReturnItem(String itemId, String commentToEvent) {
        log.info("Returning an item by user");
        rentItemService.returnItem(itemId,commentToEvent);
    }

    public void iReturnItemAndPlaceItAtGivenPlace(String itemId, String newPlaceId, String commentToEvent) {
        log.info("Returning an item by user and placing it to given place id");
        iReturnItem(itemId, commentToEvent);
        itemService.updatePlaceOfItem(itemId, newPlaceId, commentToEvent);
    }
}
