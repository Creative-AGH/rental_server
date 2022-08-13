package pl.creative.rental_server.itemManagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.creative.rental_server.entities.*;
import pl.creative.rental_server.exception.notFound.*;
import pl.creative.rental_server.handlers.ImageService;
import pl.creative.rental_server.handlers.RandomIdHandler;
import pl.creative.rental_server.itemHistoryManagement.ItemHistoryService;
import pl.creative.rental_server.itemHistoryManagement.dto.GetItemHistoryDto;
import pl.creative.rental_server.itemHistoryManagement.dto.ItemHistoryMapper;
import pl.creative.rental_server.itemManagement.dto.FillItemDto;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;
import pl.creative.rental_server.itemManagement.dto.ItemMapper;
import pl.creative.rental_server.placeManagement.dto.PlaceMapper;
import pl.creative.rental_server.repository.*;

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
        Item itemToSave = itemMapper.mapFillItemDtoToItem(fillItemDto);
        String uuid = randomIdHandler.generateUniqueIdFromTable(itemRepository);
        itemToSave.setId(uuid);
        itemToSave.setDateOfCreation(LocalDateTime.now());
        for (String categoryId : fillItemDto.getCategoriesId()) {
            if (categoryId != null) {
                categoryRepository.findById(categoryId).ifPresentOrElse(itemToSave::addCategoryToCategoryIds,
                        () -> {
                            log.error("Category with id {} is not exists", fillItemDto.getCategoriesId());
                            throw new CategoryNotFound("Category with id " + categoryId + " is not exists");
                        }
                );
            }
        }
        placeRepository.findById(fillItemDto.getPlaceId()).ifPresentOrElse(itemToSave::addPlaceToPlace,
                () -> {
                    log.error("Place with id {} is not exists", fillItemDto.getPlaceId());
                    throw new PlaceNotFound("Place with id " + fillItemDto.getPlaceId() + " is not exists");
                }
        );
        Item savedItem = itemRepository.save(itemToSave);
        itemHistoryService.addItemHistory(savedItem.getId(), "Item created", "Creating an item (with detailsOfItemBeforeEvent data!)"); //TODO add commentToEvent to endpoint
        imageService.addImages(savedItem.getId(), fillItemDto.getImages());
//        log.info("Created and saved item {}", savedItem); //logger problem
        return itemMapper.mapItemToGetItemDto(savedItem);
    }

    public List<GetItemDto> getItems() {
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

    public void replaceItem(FillItemDto fillItemDto) { // TO WHOM that endpoint
        //TODO is it necessary? we can use changeCategoriesOfItem and changePlaceOfItem
    }

    @Deprecated(forRemoval = true)
    @Transactional
    public GetItemDto changePlaceOfItem1(String itemId, String newPlaceId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        Optional<Place> placeOptional = placeRepository.findById(newPlaceId);
        if (itemOptional.isPresent() && placeOptional.isPresent()) {
//            rentHistoryService.addItemHistory(itemId,"Item updated" ,"changing place of the item"); //TODO add commentToEvent to endpoint
//            //FIXME we can not change place of the item because of  created item history before
            Item item = itemOptional.get();
            Place place = placeOptional.get();
            Place oldPlace = item.getPlace();
            log.info("Editing place of item");

            Item newItem = new Item();
            newItem.setId(item.getId());
            newItem.setName(item.getName());
            newItem.setCategories(item.getCategories());
            newItem.setStatusOfItem(item.getStatusOfItem());
            newItem.setPlace(place); //here we just need to change placeId
            List<Image> images = item.getImages();
//            images.forEach(x -> x.setValue(newItem));
            imageRepository.saveAll(images);
            newItem.setImages(images);
            oldPlace.getItems().remove(item);

            item.setImages(null);
            item.setPlace(null);
            itemRepository.save(item);
            placeRepository.save(oldPlace);
//            log.info("Editing place of item from item {} to newItem {}", item, newItem); //logger problem
            itemRepository.delete(item);
            Item savedItem = itemRepository.save(newItem);
            return itemMapper.mapItemToGetItemDto(savedItem);
        } else {
            log.error("Can not change place of the item because such id does exist");
            throw new PlaceNotFound("Can not change place of the item because such id does exist");
        }
    }

    public GetItemDto updatePlaceOfItem(String itemId, String newPlaceId, String commentToEvent) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        Optional<Place> placeOptional = placeRepository.findById(newPlaceId);
        if (itemOptional.isPresent() && placeOptional.isPresent()) {
            itemHistoryService.addItemHistory(itemId, "Item place updated", commentToEvent);
            Place newPlace = placeOptional.get();
            itemRepository.changePlaceOfItem(itemId, newPlace);

            Optional<Item> itemToReturn = itemRepository.findById(itemId);

            GetItemDto getItemDto = itemMapper.mapItemToGetItemDto(itemToReturn.get());
            getItemDto.setPlace(placeMapper.mapPlaceToGetPlaceDto(newPlace));
            return getItemDto;

        } else {
            log.error("Can not change place of the item because such id does exist");
            throw new PlaceNotFound("Can not change place of the item because such id does exist");
        }
    }

    public List<GetItemDto> getItemsByStatusOfItem(StatusOfItem statusOfItem) {
        return itemRepository.getItemsByStatusOfItem(statusOfItem).stream()
                .map(itemMapper::mapItemToGetItemDto)
                .toList();
    }

    public GetItemDto updateStatusOfItem(String itemId, StatusOfItem newStatusOfItem, String commentToEvent) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            itemHistoryService.addItemHistory(itemId, "Item status updated", commentToEvent);
            itemRepository.updateStatusOfItem(itemId, newStatusOfItem);
            GetItemDto getItemDto = itemMapper.mapItemToGetItemDto(itemOptional.get());
            getItemDto.setStatusOfItem(newStatusOfItem);
            return getItemDto;
        } else {
            log.error("Item with id {} does not exists", itemId);
            throw new ItemNotFound(String.format("Item with such id %s does not exist", itemId));
        }
    }


    public void deleteItem(String itemId, String commentToEvent, Boolean force) { //FIXME we can not delete item which has the rentHistory
        //FIXME we cant delete if item has images
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            if (force || item.getBorrowedBy() == null) {
                itemHistoryService.addDeleteItemHistory(itemId, "Item deleted", commentToEvent);
                //TODO add remove images in MINIO and add remove it from DATABASE ( potential problems )
                imageRepository.deleteAll(item.getImages());
                List<ItemHistory> itemHistoryList = itemHistoryRepository.findAllByItemId(itemId);
                itemHistoryList.forEach(x -> x.setItemId(null));
                itemHistoryRepository.saveAll(itemHistoryList);
                if(force){ //if you want to delete borrowedItem
                    item.setBorrowedBy(null);
                    itemRepository.save(item);
                }
                //TODO here add information to renthistory with desc deleted
                //TODO add here a logger
                itemRepository.delete(item);
            } else {
                Long borrowerId = item.getBorrowedBy().getId();
                log.error("You can't delete item with id {} because item is rented by someone with id {}", itemId, borrowerId);
                throw new PlaceNotFound(String.format("You can't delete item with id %s because item is rented by someone with id %d", itemId, borrowerId));
            }
        } else {
            log.error("You can't delete item with id {} because does not exist", itemId);
            throw new PlaceNotFound(String.format("You can't delete item with id %s because does not exist", itemId));
        }
    }

    public List<GetItemDto> getBorrowedItems() {
        return itemRepository.getAllByBorrowedByIsNotNull().stream().map(itemMapper::mapItemToGetItemDto).toList();
    }

    public List<GetItemDto> getNotBorrowedItems() {
        return itemRepository.getAllByBorrowedByIsNull().stream().map(itemMapper::mapItemToGetItemDto).toList();
    }

    public GetItemDto getItemByItemId(String itemId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isEmpty()) {
            log.error("Item with id {} does not exists", itemId);
            throw new ItemNotFound(String.format("Item with such id %s does not exist", itemId));
        }
        return itemMapper.mapItemToGetItemDto(itemOptional.get());
    }

    public GetItemDto changeBorrowerOfItem(String itemId, Long newAccountId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        Optional<Account> accountOptional = accountRepository.findById(newAccountId);
        if (itemOptional.isEmpty()) {
            log.error("Item with id {} does not exists", itemId);
            throw new ItemNotFound(String.format("Item with such id %s does not exist", itemId));
        }
        if (accountOptional.isEmpty()) {
            log.error("Account with id {} does not exists", newAccountId);
            throw new AccountNotFound(String.format("Item with such id %d does not exist", newAccountId));
        }
        if (itemOptional.get().getBorrowedBy() == null) {
            log.error("Can not change borrower of item with id {} because this item is not borrowed by anyone", itemId);
            throw new BorrowerException(String.format("Can not change borrower of item with with id %s because this item is not borrowed by anyone", itemId));
        }
        itemRepository.changeBorrowerOfItem(itemId, accountOptional.get());
        return itemMapper.mapItemToGetItemDto(itemRepository.findById(itemId).get()); //FIXME should return with new borrower id
    }

    public List<GetItemHistoryDto> getHistoryOfItem(String itemId) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if(optionalItem.isPresent()){
            return optionalItem.get()
                    .getHistory()
                    .stream()
                    .map(itemHistoryMapper::mapItemHistoryToGetItemHistoryDto).toList();
        }else {
            log.error("Item with such id {} does not exists", itemId);
            throw new ItemNotFound(String.format("Item with such id %s does not exist", itemId));
        }
    }

    public void forceDeleteBorrowedItem(String itemId, String commentToEvent) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            if (item.getBorrowedBy() != null) { //if item is borrowed by someone
                imageRepository.deleteAll(item.getImages());

                List<ItemHistory> itemHistoryList = itemHistoryRepository.findAllByItemId(itemId);
                itemHistoryList.forEach(x -> x.setItemId(null));
                itemHistoryRepository.saveAll(itemHistoryList);

                item.setBorrowedBy(null);
                itemRepository.save(item);

                //TODO here add information to renthistory with desc deleted
                //TODO add here a logger
                itemRepository.delete(item);
            } else {
                Long borrowerId = item.getBorrowedBy().getId();
                log.error("You can't delete item with id {} because item is rented by someone with id {}", itemId, borrowerId);
                throw new PlaceNotFound(String.format("You can't delete item with id %s because item is rented by someone with id %d", itemId, borrowerId));
            }
        } else {
            log.error("You can't delete item with id {} because does not exist", itemId);
            throw new PlaceNotFound(String.format("You can't delete item with id %s because does not exist", itemId));
        }
    }

    //TODO changeCategoriesOfItem
    //

}
