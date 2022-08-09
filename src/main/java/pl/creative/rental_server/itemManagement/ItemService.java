package pl.creative.rental_server.itemManagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.Place;
import pl.creative.rental_server.entities.StatusOfItem;
import pl.creative.rental_server.exception.notFound.CategoryNotFound;
import pl.creative.rental_server.exception.notFound.ItemNotFound;
import pl.creative.rental_server.exception.notFound.PlaceNotFound;
import pl.creative.rental_server.handlers.RandomIdHandler;
import pl.creative.rental_server.itemManagement.dto.FillItemDto;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;
import pl.creative.rental_server.itemManagement.dto.ItemMapper;
import pl.creative.rental_server.repository.CategoryRepository;
import pl.creative.rental_server.repository.ItemRepository;
import pl.creative.rental_server.repository.PlaceRepository;

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

    @Transactional
    public GetItemDto addItem(FillItemDto fillItemDto) {
        Item itemToSave = itemMapper.mapFillItemDtoToItem(fillItemDto);
        String uuid = randomIdHandler.generateUniqueIdFromTable(itemRepository);
        itemToSave.setId(uuid);
        itemToSave.setDateOfCreation(LocalDateTime.now());
        for (String categoryId : fillItemDto.getCategoriesId()) {
            categoryRepository.findById(categoryId).ifPresentOrElse(itemToSave::addCategoryToCategoryIds,
                    () -> {
                        log.error("Category with id {} is not exists", fillItemDto.getCategoriesId());
                        throw new CategoryNotFound("Category with id " + categoryId + " is not exists");
                    }
            );
        }
        placeRepository.findById(fillItemDto.getPlaceId()).ifPresentOrElse(itemToSave::addPlaceToPlace,
                () -> {
                    log.error("Place with id {} is not exists", fillItemDto.getPlaceId());
                    throw new PlaceNotFound("Place with id " + fillItemDto.getPlaceId() + " is not exists");
                }
        );
        Item savedItem = itemRepository.save(itemToSave);
//        log.info("Created and saved item {}", savedItem); //logger problem
        return itemMapper.mapItemToGetItemDto(savedItem);
    }

    public List<GetItemDto> getItems() {
        List<GetItemDto> listOfGetItemDto = new ArrayList<>();
        Iterable<Item> items = itemRepository.findAll();
        for (Item item : items) {
            listOfGetItemDto.add(itemMapper.mapItemToGetItemDto(item));
        }
        return listOfGetItemDto;
    }

    public void replaceItem(FillItemDto fillItemDto) {
        //TODO is it necessary? we can use changeCategoriesOfItem and changePlaceOfItem
    }

    @Transactional
    public GetItemDto changePlaceOfItem(String itemId, String newPlaceId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        Optional<Place> placeOptional = placeRepository.findById(newPlaceId);
        if (itemOptional.isPresent() && placeOptional.isPresent()) {
            Item item = itemOptional.get();
//            log.info("Editing place of item from {} to {}", item.getPlace().getId(), newPlaceId);

            Item newItem = new Item();
            newItem.setId(item.getId());
            newItem.setName(item.getName());
            newItem.setCategories(item.getCategories());
            newItem.setStatusOfItem(item.getStatusOfItem());
            newItem.setPlace(placeOptional.get()); //here we just need to change placeId

//            log.info("Editing place of item from item {} to newItem {}", item, newItem); //logger problem
            itemRepository.delete(item);
            Item savedItem = itemRepository.save(newItem);
            return itemMapper.mapItemToGetItemDto(savedItem);

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

    public GetItemDto updateStatusOfItem(String itemId, StatusOfItem newStatusOfItem) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            itemRepository.updateStatusOfItem(itemId, newStatusOfItem);
            GetItemDto getItemDto = itemMapper.mapItemToGetItemDto(itemOptional.get());
            getItemDto.setStatusOfItem(newStatusOfItem);
            return getItemDto;
        } else {
            log.error("Item with id {} does not exists", itemId);
            throw new ItemNotFound(String.format("Item with such id %s does not exist", itemId));
        }
    }

    @Transactional
    public void deleteItem(String itemId) {
        itemRepository.findById(itemId).ifPresentOrElse(this::removeItem,
                () -> {
                    log.error("You can't delete item with {} id because does not exist", itemId);
                    throw new PlaceNotFound(String.format("You can't delete item with %s id because does not exist", itemId));
                }
        );
    }

    private void removeItem(Item item) {
        itemRepository.delete(item);
    }

    public List<GetItemDto> getBorrowedItems() {
        return itemRepository.getAllByBorrowedByIsNotNull().stream().map(itemMapper::mapItemToGetItemDto).toList();
    }

    public List<GetItemDto> getNotBorrowedItems() {
        return itemRepository.getAllByBorrowedByIsNull().stream().map(itemMapper::mapItemToGetItemDto).toList();
    }

    //TODO changeCategoriesOfItem
    //

}
