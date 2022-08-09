package pl.creative.rental_server.rentHistoryManagement;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.Account;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.RentHistory;
import pl.creative.rental_server.exception.notFound.AccountNotFound;
import pl.creative.rental_server.exception.notFound.ItemNotFound;
import pl.creative.rental_server.rentHistoryManagement.dto.GetRentHistoryDto;
import pl.creative.rental_server.rentHistoryManagement.dto.RentHistoryMapper;
import pl.creative.rental_server.repository.AccountRepository;
import pl.creative.rental_server.repository.ItemRepository;
import pl.creative.rental_server.repository.RentHistoryRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class RentHistoryService {
    private final RentHistoryRepository rentHistoryRepository;
    private final ItemRepository itemRepository;
    private final AccountRepository accountRepository;
    private final RentHistoryMapper rentHistoryMapper;

    @Transactional
    public void addRentHistory(Long accountId, String itemId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalAccount.isPresent() && optionalItem.isPresent()) {
            Account account = optionalAccount.get();
            Item item = optionalItem.get();
            RentHistory rentHistory = new RentHistory();
            rentHistory.setBorrowerAccount(account);
            rentHistory.setItem(item);
            if (item.getBorrowedBy() == null) {
                rentHistory.setTimeOfReturning(LocalDateTime.now());
                rentHistory.setDescription("Item returned");
            } else {
                rentHistory.setTimeOfRenting(LocalDateTime.now());
                rentHistory.setDescription("Item rented");
            }
            rentHistoryRepository.save(rentHistory);
        } else {
            if (optionalItem.isEmpty()) {
                log.error("Item with id {} does not exist", itemId);
                throw new ItemNotFound(String.format("Item with id %s does not exist", itemId));
            }
            log.error("Account with id {} does not exist", accountId);
            throw new AccountNotFound(String.format("Account with id %s does not exist", accountId));
        }
    }


    public List<GetRentHistoryDto> getAllRentHistory() {
        List<GetRentHistoryDto> listOfGetRentHistoryDto = new ArrayList<>();
        Iterable<RentHistory> listOfRentHistory = rentHistoryRepository.findAll();
        for (RentHistory rentHistory : listOfRentHistory) {
            listOfGetRentHistoryDto.add(rentHistoryMapper.mapRentHistoryToGetRentHistoryDto(rentHistory));
        }
        return listOfGetRentHistoryDto;
    }
}
