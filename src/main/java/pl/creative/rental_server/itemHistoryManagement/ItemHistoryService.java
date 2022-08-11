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
import pl.creative.rental_server.repository.ItemRepository;
import pl.creative.rental_server.repository.ItemHistoryRepository;

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
    @Transactional //warning / check it
    public void addRentReturnHistory(Long accountId, String typeOfEvent, String itemId, String commentToEvent) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalAccount.isPresent() && optionalItem.isPresent()) {
            Account account = optionalAccount.get();
            Item item = optionalItem.get();
            ItemHistory itemHistory = new ItemHistory();
            itemHistory.setAccount(account);
            itemHistory.setItem(item);
            if (commentToEvent != null)
                itemHistory.setCommentToEvent(commentToEvent);

            String itemDetailsHistoryString = createItemDetailsHistory(item);
            itemHistory.setDetailsOfItemBeforeEvent(itemDetailsHistoryString);

            itemHistory.setTimeOfEvent(LocalDateTime.now());
            itemHistory.setTypeOfEvent(typeOfEvent);

            itemHistoryRepository.save(itemHistory);
        } else {
            if (optionalItem.isEmpty()) {
                log.error("Item with id {} does not exist", itemId);
                throw new ItemNotFound(String.format("Item with id %s does not exist", itemId));
            }
            log.error("Account with id {} does not exist", accountId);
            throw new AccountNotFound(String.format("Account with id %s does not exist", accountId));
        }
    }

    public void addDeleteHistory(String itemId, String typeOfEvent, String commentToEvent) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            ItemHistory itemHistory = new ItemHistory();
//            itemHistory.setItem(item); //this is not needed
            itemHistory.setTypeOfEvent(typeOfEvent);
            itemHistory.setTimeOfEvent(LocalDateTime.now());
            if (commentToEvent != null) {
                itemHistory.setCommentToEvent(commentToEvent);
            }

            String itemDetailsHistory = createItemDetailsHistory(item);
            itemHistory.setDetailsOfItemBeforeEvent(itemDetailsHistory);

            itemHistoryRepository.save(itemHistory);
        } else {
            log.error("Item with id {} does not exist", itemId);
            throw new ItemNotFound(String.format("Item with id %s does not exist", itemId));
        }
    }

    public void addItemHistory(String itemId, String typeOfEvent, String commentToEvent) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            ItemHistory itemHistory = new ItemHistory();
            itemHistory.setItem(item);
            itemHistory.setTypeOfEvent(typeOfEvent);
            itemHistory.setTimeOfEvent(LocalDateTime.now());
            if (commentToEvent != null) {
                itemHistory.setCommentToEvent(commentToEvent);
            }
            String itemDetailsHistory = createItemDetailsHistory(item);
            itemHistory.setDetailsOfItemBeforeEvent(itemDetailsHistory);

            itemHistoryRepository.save(itemHistory);
        } else {
            log.error("Item with id {} does not exist", itemId);
            throw new ItemNotFound(String.format("Item with id %s does not exist", itemId));
        }
    }

    public List<GetItemHistoryDto> getAllItemHistory() {
        List<GetItemHistoryDto> listOfGetItemHistoryDto = new ArrayList<>();
        Iterable<ItemHistory> listOfItemHistory = itemHistoryRepository.findAll();
        for (ItemHistory itemHistory : listOfItemHistory) {
            listOfGetItemHistoryDto.add(itemHistoryMapper.mapItemHistoryToGetItemHistoryDto(itemHistory));
        }
        return listOfGetItemHistoryDto;
    }

    private String createItemDetailsHistory(Item item) { //could be a problem because of length of string in database
        ItemBuilder itemBuilder = new ItemBuilder();
        itemBuilder.addItemId(item.getId());
        if (item.getDescription() != null) {
            itemBuilder.addItemDescription(item.getDescription());
        }
        if (item.getStatusOfItem() != null) {
            itemBuilder.addItemStatusOfItem(item.getStatusOfItem().getCode());
        }
        if (item.getPlace() != null) {
            itemBuilder.addItemPlaceId(item.getPlace().getId())
                    .addItemPlaceName(item.getPlace().getName())
                    .addItemPlaceDescription(item.getPlace().getDescription());
        }
        ItemDetailsHistory itemDetailsHistory = itemBuilder.build();
        return itemDetailsHistory.toString();
    }
}
