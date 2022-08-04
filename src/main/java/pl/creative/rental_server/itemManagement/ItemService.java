package pl.creative.rental_server.itemManagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.exception.notFound.CategoryNotFound;
import pl.creative.rental_server.handlers.RandomIdHandler;
import pl.creative.rental_server.itemManagement.dto.FillItemDto;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;
import pl.creative.rental_server.itemManagement.dto.ItemMapper;
import pl.creative.rental_server.repository.ItemRepository;
import pl.creative.rental_server.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final CategoryRepository categoryRepository;
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
        Item savedItem = itemRepository.save(itemToSave);
        log.info("Created and saved item {}", savedItem);
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
}
