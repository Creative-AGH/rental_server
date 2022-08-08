package pl.creative.rental_server.rentItem;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.Account;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.Place;
import pl.creative.rental_server.exception.notFound.AccountNotFound;
import pl.creative.rental_server.exception.notFound.BorrowerException;
import pl.creative.rental_server.exception.notFound.ItemNotFound;
import pl.creative.rental_server.exception.notFound.NotFoundException;
import pl.creative.rental_server.repository.AccountRepository;
import pl.creative.rental_server.repository.ItemRepository;
import pl.creative.rental_server.repository.PlaceRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class RentItemByModeratorService {
    private final ItemRepository itemRepository;
    private final AccountRepository accountRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public void rentItem(Long accountId, String itemId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (itemOptional.isPresent() && accountOptional.isPresent()) {
            if (itemOptional.get().getBorrowedBy() != null) {
                Long borrowerId = itemOptional.get().getBorrowedBy().getId();
                log.error("Item with id {} is already borrowed by someone with id {}", itemId, borrowerId);
                throw new BorrowerException(String.format("Item with id %s is already borrowed by someone with id %s", itemId, borrowerId));
            }
            itemOptional.map(Item::getPlace).map(x -> x.getItems().remove(itemOptional.get()));
            itemOptional.ifPresent(x -> x.setBorrowedBy(accountOptional.get()));
            itemOptional.ifPresent(x -> x.setPlace(null));
        } else {
            if (itemOptional.isEmpty() && accountOptional.isEmpty()) {
                log.error("Item with id {} does not exist", itemId);
                log.error("Account with id {} does not exist", accountId);
                throw new NotFoundException(String.format("Item with id %s and account with id %s does not exist", itemId, accountId));
            }
            if (itemOptional.isEmpty()) {
                log.error("Item with id {} does not exist", itemId);
                throw new ItemNotFound(String.format("Item with id %s does not exist", itemId));
            }
            log.error("Account with id {} does not exist", accountId);
            throw new AccountNotFound(String.format("Account with id %s does not exist", accountId));
        }
    }

    @Transactional
    public void returnItem(String itemId) {
        itemRepository.findById(itemId).ifPresentOrElse(x -> {
            if (x.getBorrowedBy() == null) {
                log.error("Can not return item with id {} because this item is not borrowed by anyone", itemId);
                throw new BorrowerException(String.format("Can not return item with id %s because this item is not borrowed by anyone", itemId));
            }
            x.setBorrowedBy(null);
            Optional<Place> abstractPlaceOptional = placeRepository.findById("0");
            abstractPlaceOptional.ifPresent(x::setPlace);
        }, () -> {
            log.error("Can not return item because item with id {} does not exist", itemId);
            throw new ItemNotFound(String.format("Can not return item with id %s does not exist", itemId));
        });
    }

}
