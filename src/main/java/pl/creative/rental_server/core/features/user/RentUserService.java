package pl.creative.rental_server.core.features.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
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

    public void rentItemForMe(String itemId, String commentToEvent) {
        log.info("Renting an item by user");
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("User account email: {}", accountEmail);
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
}
