package pl.creative.rental_server.ItemManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.creative.rental_server.Entities.Item;
import pl.creative.rental_server.ItemManagement.dto.FillItemDto;
import pl.creative.rental_server.ItemManagement.dto.GetItemDto;
import pl.creative.rental_server.ItemManagement.dto.ItemMapper;
import pl.creative.rental_server.Repository.ItemRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;


    @Transactional
    public GetItemDto addItem(FillItemDto fillItemDto) {
        Item itemToSave = itemMapper.mapFillItemDtoToItem(fillItemDto);
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (itemRepository.findById(uuid).isPresent());
        itemToSave.setId(uuid);
        itemToSave.setDateOfCreate(LocalDateTime.now());
        Item savedItem = itemRepository.save(itemToSave);
        return itemMapper.mapItemToGetItemDto(savedItem);
        //TODO upload files to ingur or other service
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
