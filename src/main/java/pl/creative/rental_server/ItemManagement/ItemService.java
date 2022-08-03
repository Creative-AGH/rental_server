package pl.creative.rental_server.ItemManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.creative.rental_server.Entities.Item;
import pl.creative.rental_server.Handlers.RandomIdHandler;
import pl.creative.rental_server.ItemManagement.dto.FillItemDto;
import pl.creative.rental_server.ItemManagement.dto.GetItemDto;
import pl.creative.rental_server.ItemManagement.dto.ItemMapper;
import pl.creative.rental_server.Repository.ItemRepository;
import pl.creative.rental_server.Repository.TypeOfItemRepository;
import pl.creative.rental_server.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final TypeOfItemRepository typeOfItemRepository;
    private final RandomIdHandler randomIdHandler;

    @Transactional
    public GetItemDto addItem(FillItemDto fillItemDto) {
        Item itemToSave = itemMapper.mapFillItemDtoToItem(fillItemDto);
        String uuid = randomIdHandler.generateUniqueIdFromTable(itemRepository);
        itemToSave.setId(uuid);
        itemToSave.setDateOfCreation(LocalDateTime.now());
        for (String typeId : fillItemDto.getTypesOfItemId()) {
            typeOfItemRepository.findById(typeId).ifPresentOrElse(itemToSave::addTypeOfItemToTypeOfItemIds,
                    () -> {
                        throw new NotFoundException("Category with id " + typeId + " is not exists");
                    }
            );
        }
        Item savedItem = itemRepository.save(itemToSave);
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
