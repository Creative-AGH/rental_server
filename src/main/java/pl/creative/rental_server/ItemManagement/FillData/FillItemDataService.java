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


    public void addItem(FillItemDto dto, String ItemTemplateId) {
        Item item = fillItemMapper.mapItemDtoToItem(dto);
        String uuid = randomIdHandler.generateUniqueIdFromTable(itemRepository);
        item.setId(uuid);

        // TODO upload files to ingur or other service

        itemRepository.save(item);
    }
}
