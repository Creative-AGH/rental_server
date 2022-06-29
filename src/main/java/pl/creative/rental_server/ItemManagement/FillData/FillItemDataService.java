package pl.creative.rental_server.ItemManagement.FillData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.creative.rental_server.Entities.Category;
import pl.creative.rental_server.Entities.Item;
import pl.creative.rental_server.Repository.CategoryRepository;
import pl.creative.rental_server.Repository.ItemRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FillItemDataService {
    private final ItemRepository itemRepository;
    private final FillItemMapper fillItemMapper;


    @Transactional
    public void addItem(FillItemDto dto, String categoryId) {
        Item item = fillItemMapper.mapItemDtoToItem(dto);

        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (itemRepository.findById(uuid).isPresent());
        item.setId(uuid);

        //TODO upload files to ingur or other service

        itemRepository.save(item);
    }
}
