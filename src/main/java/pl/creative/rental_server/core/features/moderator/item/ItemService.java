package pl.creative.rental_server.core.features.moderator.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.creative.rental_server.db.entities.*;
import pl.creative.rental_server.core.global.exception.notFound.*;
import pl.creative.rental_server.core.global.handlersAndUtils.ImageService;
import pl.creative.rental_server.core.global.handlersAndUtils.RandomIdHandler;
import pl.creative.rental_server.core.features.moderator.item.history.ItemHistoryService;
import pl.creative.rental_server.core.features.moderator.item.history.dto.GetItemHistoryDto;
import pl.creative.rental_server.core.features.moderator.item.history.dto.ItemHistoryMapper;
import pl.creative.rental_server.core.features.moderator.item.dto.FillItemDto;
import pl.creative.rental_server.core.features.moderator.item.dto.GetItemDto;
import pl.creative.rental_server.core.features.moderator.item.dto.ItemMapper;
import pl.creative.rental_server.core.features.moderator.place.dto.PlaceMapper;
import pl.creative.rental_server.db.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final CategoryRepository categoryRepository;
    private final PlaceRepository placeRepository;
    private final RandomIdHandler randomIdHandler;
    private final AccountRepository accountRepository;
    private final ItemHistoryRepository itemHistoryRepository;
    private final ItemHistoryService itemHistoryService;
    private final PlaceMapper placeMapper;
    private final ItemHistoryMapper itemHistoryMapper;

    private final ImageRepository imageRepository;
    private final ImageService imageService;

    @Transactional
    public GetItemDto addItem(FillItemDto fillItemDto) {
        log.info("Adding new item");
        Item itemToSave = itemMapper.mapFillItemDtoToItem(fillItemDto);
        String uuid = randomIdHandler.generateUniqueIdFromTable(itemRepository);
        itemToSave.setId(uuid);
        itemToSave.setDateOfCreation(LocalDateTime.now());
        for (String categoryId : fillItemDto.getCategoriesId()) {
            if (categoryId != null) {
                categoryRepository.findById(categoryId).ifPresentOrElse(itemToSave::addCategoryToCategoryIds,
                        () -> {
                            log.error("Category with that id {} does not exist");
                            throw new CategoryNotFound(String.format("Category with that id %s does not exist", categoryId));
                        }
                );
            }
        }
        placeRepository.findById(fillItemDto.getPlaceId()).ifPresentOrElse(itemToSave::addPlaceToPlace,
                () -> {
                    log.error("Place with that id does not exist");
                    throw new PlaceNotFound("Place with that id does not exist");
                }
        );
        Item savedItem = itemRepository.save(itemToSave);
        itemHistoryService.addItemHistory(savedItem.getId(), "Item created", "Creating an item (with detailsOfItemBeforeEvent data!)");
        imageService.addImages(savedItem.getId(), fillItemDto.getImages());
        log.info("Successfully created and saved item");
        return itemMapper.mapItemToGetItemDto(savedItem);
    }

    public List<GetItemDto> getAllItems() {
        log.info("Getting all items");
        List<GetItemDto> listOfGetItemDto = new ArrayList<>();
//        PageRequest twoElementsOfFirstPage = PageRequest.of(0, 2);
//        Page<Item> items = itemRepository.findAll(twoElementsOfFirstPage);
//        int totalPages = items.getTotalPages();
        Iterable<Item> items = itemRepository.findAll();
        for (Item item : items) {
            listOfGetItemDto.add(itemMapper.mapItemToGetItemDto(item));
        }
        return listOfGetItemDto;
    }

    @Transactional
    public void replaceItem(FillItemDto fillItemDto) { // TO WHOM that endpoint
        //TODO is it necessary? we can use changeCategoriesOfItem and changePlaceOfItem
    }

    @Transactional
    public GetItemDto updatePlaceOfItem(String itemId, String newPlaceId, String commentToEvent) {
        log.info("Updating a place of the item");
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        Optional<Place> placeOptional = placeRepository.findById(newPlaceId);
        if (itemOptional.isPresent() && placeOptional.isPresent()) {
            itemHistoryService.addItemHistory(itemId, "Item place updated", commentToEvent);
            Place newPlace = placeOptional.get();
            itemRepository.changePlaceOfItem(itemId, newPlace);
            log.info("Successfully updated a place of item");

            Optional<Item> itemToReturn = itemRepository.findById(itemId);
            GetItemDto getItemDto = itemMapper.mapItemToGetItemDto(itemToReturn.get());
            getItemDto.setPlace(placeMapper.mapPlaceToGetPlaceDto(newPlace));
            return getItemDto;
        } else {
            log.error("Can not change a place of the item because such id does exist");
            throw new PlaceNotFound("Can not change a place of the item because such id does exist");
        }
    }

    public List<GetItemDto> getAllItemsByStatusOfItem(StatusOfItem statusOfItem) {
        log.info("Getting all items by status of the item");
        return itemRepository.getItemsByStatusOfItem(statusOfItem).stream()
                .map(itemMapper::mapItemToGetItemDto)
                .toList();
    }

    @Transactional
    public GetItemDto updateStatusOfItem(String itemId, StatusOfItem newStatusOfItem, String commentToEvent) {
        log.info("Updating a status of the item");
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            itemHistoryService.addItemHistory(itemId, "Item status updated", commentToEvent);
            itemRepository.updateStatusOfItem(itemId, newStatusOfItem);
            log.info("Successfully updated a status of the item");
            GetItemDto getItemDto = itemMapper.mapItemToGetItemDto(itemOptional.get());
            getItemDto.setStatusOfItem(newStatusOfItem);
            return getItemDto;
        } else {
            log.error("Item with that id {} does not exist", itemId);
            throw new ItemNotFound(String.format("Item with that id %s does not exist", itemId));
        }
    }

    @Transactional
    public void deleteItem(String itemId, String commentToEvent, Boolean force) {
        log.info("Deleting an item");
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            if (force || item.getBorrowedBy() == null) {
                if (force) {
                    itemHistoryService.addItemHistory(itemId, "Item deleted by force", commentToEvent);
                } else {
                    itemHistoryService.addItemHistory(itemId, "Item deleted", commentToEvent);
                }
                //TODO add remove images in MINIO and add remove it from DATABASE (potential problems)
                imageRepository.deleteAll(item.getImages());
                List<ItemHistory> itemHistoryList = itemHistoryRepository.findAllByItemId(itemId);
                itemHistoryList.forEach(x -> x.setItemId(null));
                itemHistoryRepository.saveAll(itemHistoryList);
                if (force) {
                    item.setBorrowedBy(null);
                    itemRepository.save(item);
                }
                itemRepository.delete(item);
                log.info("Successfully deleted an item with id {}", itemId);
            } else {
                Long borrowerId = item.getBorrowedBy().getId();
                log.error("You can't delete item with that id {} because item is rented by someone with id {}", itemId, borrowerId);
                throw new PlaceNotFound(String.format("You can't delete item with that id %s because item is rented by someone with id %d", itemId, borrowerId));
            }
        } else {
            log.error("You can't delete item with that id {} because does not exist", itemId);
            throw new PlaceNotFound(String.format("You can't delete item with that id %s because does not exist", itemId));
        }
    }

    public List<GetItemDto> getBorrowedItems() {
        log.info("Getting all borrowed items");
        return itemRepository.getAllByBorrowedByIsNotNull().stream().map(itemMapper::mapItemToGetItemDto).toList();
    }

    public List<GetItemDto> getNotBorrowedItems() {
        log.info("Getting all not borrowed items");
        return itemRepository.getAllByBorrowedByIsNull().stream().map(itemMapper::mapItemToGetItemDto).toList();
    }

    public GetItemDto getItemByItemId(String itemId) {
        log.info("Getting an item by id");
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isEmpty()) {
            log.error("Item with the id {} does not exists", itemId);
            throw new ItemNotFound(String.format("Item with that id %s does not exist", itemId));
        }
        return itemMapper.mapItemToGetItemDto(itemOptional.get());
    }

    @Transactional
    public GetItemDto updateBorrowerOfItem(String itemId, Long newAccountId, String commentToEvent) {
        log.info("Updating a borrower of the item");
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        Optional<Account> accountOptional = accountRepository.findById(newAccountId);
        if (itemOptional.isEmpty()) {
            log.error("Item with that id {} does not exist", itemId);
            throw new ItemNotFound(String.format("Item with that id %s does not exist", itemId));
        }
        if (accountOptional.isEmpty()) {
            log.error("Account with that id {} does not exist", newAccountId);
            throw new AccountNotFound(String.format("Item with that id %d does not exist", newAccountId));
        }
        if (itemOptional.get().getBorrowedBy() == null) {
            log.error("Can not change borrower of item with that id {} because this item is not borrowed by anyone", itemId);
            throw new BorrowerException(String.format("Can not change borrower of item with that id %s because this item is not borrowed by anyone", itemId));
        }
        itemHistoryService.addItemHistory(itemId, "Borrower of item updated", commentToEvent);
        itemRepository.changeBorrowerOfItem(itemId, accountOptional.get());
        log.info("Successfully updated a borrower of the item");
        return itemMapper.mapItemToGetItemDto(itemRepository.findById(itemId).get()); //FIXME should return with new borrower id
    }

    public List<GetItemHistoryDto> getAllHistoryOfItem(String itemId) {
        log.info("Getting history of the item");
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            return optionalItem.get()
                    .getHistory()
                    .stream()
                    .map(itemHistoryMapper::mapItemHistoryToGetItemHistoryDto).toList();
        } else {
            log.error("Item with that id {} does not exist", itemId);
            throw new ItemNotFound(String.format("Item with that id %s does not exist", itemId));
        }
    }

    public List<GetItemDto> getAllItemsFromDefaultPlace() {
        log.info("Getting all items in default place");
        String defaultPlaceId = "0";
        Optional<Place> optionalPlace = placeRepository.findById(defaultPlaceId);
        if (optionalPlace.isPresent()) {
            return optionalPlace.get().getItems().stream().map(itemMapper::mapItemToGetItemDto).toList();
        } else {
            log.error("Place with that id {} does not exist (defaultPlace)", defaultPlaceId);
            throw new PlaceNotFound(String.format("Place with that id -  %s does not exist (defaultPlace)", defaultPlaceId));
        }
    }

    //TODO changeCategoriesOfItem

}
