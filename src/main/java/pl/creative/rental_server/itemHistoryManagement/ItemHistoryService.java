package pl.creative.rental_server.itemHistoryManagement;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.Account;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.ItemHistory;
import pl.creative.rental_server.exception.notFound.AccountNotFound;
import pl.creative.rental_server.exception.notFound.ItemNotFound;
import pl.creative.rental_server.itemHistoryManagement.dto.GetItemHistoryDto;
import pl.creative.rental_server.itemHistoryManagement.dto.ItemHistoryMapper;
import pl.creative.rental_server.repository.AccountRepository;
import pl.creative.rental_server.repository.ItemHistoryRepository;
import pl.creative.rental_server.repository.ItemRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class ItemHistoryService {
    private final ItemHistoryRepository itemHistoryRepository;
    private final ItemRepository itemRepository;
    private final AccountRepository accountRepository;
    private final ItemHistoryMapper itemHistoryMapper;

    //    public void addRecordHistory(Function<T,R>()) //TODO we can use a strategy design pattern
    @Transactional
    public void addRentReturnItemHistory(Long accountId, String typeOfEvent, String itemId, String commentToEvent) {
        log.info("Adding renting/returning history");
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalAccount.isPresent() && optionalItem.isPresent()) {
            Account account = optionalAccount.get();
            Item item = optionalItem.get();

            ItemHistory itemHistory = new ItemHistory();
            itemHistory.setItemId(item.getId());
            itemHistory.setTimeOfEvent(LocalDateTime.now());
            itemHistory.setTypeOfEvent(typeOfEvent);
            itemHistory.setAccount(account);
            itemHistory.setDetailsOfItemBeforeEvent(createItemDetailsHistory(item));
            if (commentToEvent != null) itemHistory.setCommentToEvent(commentToEvent);

            itemHistoryRepository.save(itemHistory);
            log.info("Successfully added renting/returning history");
        } else {
            if (optionalItem.isEmpty()) {
                log.error("Item with id {} does not exist", itemId);
                throw new ItemNotFound(String.format("Item with id %s does not exist", itemId));
            }
            log.error("Account with id {} does not exist", accountId);
            throw new AccountNotFound(String.format("Account with id %s does not exist", accountId));
        }
    }

    public void addDeleteItemHistory(String itemId, String typeOfEvent, String commentToEvent) {
        log.info("Adding deleting history");
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();

            ItemHistory itemHistory = new ItemHistory();
            itemHistory.setItemId(item.getId());
            itemHistory.setTimeOfEvent(LocalDateTime.now());
            itemHistory.setTypeOfEvent(typeOfEvent);
            itemHistory.setDetailsOfItemBeforeEvent(createItemDetailsHistory(item));
            if (commentToEvent != null) itemHistory.setCommentToEvent(commentToEvent);

            itemHistoryRepository.save(itemHistory);
            log.info("Successfully added deleting item history");
        } else {
            log.error("Item with id {} does not exist", itemId);
            throw new ItemNotFound(String.format("Item with id %s does not exist", itemId));
        }
    }

    @Transactional
    public void addItemHistory(String itemId, String typeOfEvent, String commentToEvent) {
        log.info("Adding item history");
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();

            ItemHistory itemHistory = new ItemHistory();
            itemHistory.setItemId(item.getId());
            itemHistory.setTimeOfEvent(LocalDateTime.now());
            itemHistory.setTypeOfEvent(typeOfEvent);
            itemHistory.setDetailsOfItemBeforeEvent(createItemDetailsHistory(item));
            if (commentToEvent != null) itemHistory.setCommentToEvent(commentToEvent);

            itemHistoryRepository.save(itemHistory);
            log.info("Successfully added item history");
        } else {
            log.error("Item with id {} does not exist", itemId);
            throw new ItemNotFound(String.format("Item with id %s does not exist", itemId));
        }
    }

    public List<GetItemHistoryDto> getAllItemHistory() {
        log.info("Getting all item history");
        List<GetItemHistoryDto> listOfGetItemHistoryDto = new ArrayList<>();
        Iterable<ItemHistory> listOfItemHistory = itemHistoryRepository.findAll();
        for (ItemHistory itemHistory : listOfItemHistory) {
            listOfGetItemHistoryDto.add(itemHistoryMapper.mapItemHistoryToGetItemHistoryDto(itemHistory));
        }
        return listOfGetItemHistoryDto;
    }

    private String createItemDetailsHistory(Item item) {
        log.info("Creating item details history");
        ItemDetailsBuilder itemDetailsBuilder = new ItemDetailsBuilder();
        itemDetailsBuilder.addItemId(item.getId());
        if (item.getDescription() != null) {
            itemDetailsBuilder.addItemDescription(item.getDescription());
        }
        if (item.getStatusOfItem() != null) {
            itemDetailsBuilder.addItemStatusOfItem(item.getStatusOfItem().getCode());
        }
        if (item.getPlace() != null) {
            itemDetailsBuilder.addItemPlaceId(item.getPlace().getId())
                    .addItemPlaceName(item.getPlace().getName())
                    .addItemPlaceDescription(item.getPlace().getDescription());
        }
        ItemDetailsHistory itemDetailsHistory = itemDetailsBuilder.build();
        return itemDetailsHistory.toString();
    }
}
