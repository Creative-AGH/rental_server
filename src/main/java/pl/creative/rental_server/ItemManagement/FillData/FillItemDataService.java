package pl.creative.rental_server.ItemManagement.FillData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.Entities.Item;
import pl.creative.rental_server.Handlers.RandomIdHandler;
import pl.creative.rental_server.ItemManagement.dto.FillItemDto;
import pl.creative.rental_server.Repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class FillItemDataService {
    private final ItemRepository itemRepository;
    private final FillItemMapper fillItemMapper;
    private final RandomIdHandler randomIdHandler;


    public void addItem(FillItemDto fillItemDto, String ItemTemplateId) {
        Item item = fillItemMapper.mapItemDtoToItem(fillItemDto);
        String uuid = randomIdHandler.generateUniqueIdFromTable(itemRepository);
        item.setId(uuid);
        //put item to template
        //put template to item
        // TODO upload files to ingur or other service

        itemRepository.save(item);
    }
}
