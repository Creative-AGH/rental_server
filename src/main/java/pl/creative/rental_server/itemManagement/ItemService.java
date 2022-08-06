package pl.creative.rental_server.itemManagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.Place;
import pl.creative.rental_server.exception.notFound.CategoryNotFound;
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
        //TODO
    }

    @Transactional
    public void changePlaceOfItem(String itemId, String newPlaceId) {
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
            itemRepository.save(newItem);

        } else {
            log.error("Can not change place of the item because such id does exist");
            throw new PlaceNotFound("Can not change place of the item because such id does exist");
        }
    }

}
