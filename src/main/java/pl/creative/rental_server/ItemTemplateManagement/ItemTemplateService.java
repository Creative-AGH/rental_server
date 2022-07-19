package pl.creative.rental_server.ItemTemplateManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.ItemTemplateManagement.dto.ItemTemplateMapper;
import pl.creative.rental_server.ItemTemplateManagement.dto.FillItemTemplateDto;
import pl.creative.rental_server.ItemTemplateManagement.dto.GetItemTemplateDto;
import pl.creative.rental_server.Entities.ItemTemplate;
import pl.creative.rental_server.Repository.ItemTemplateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemTemplateService {
    private final ItemTemplateRepository itemTemplateRepository;
    private final ItemTemplateMapper itemTemplateMapper;


    public GetItemTemplateDto addCategory(FillItemTemplateDto dto) {
        ItemTemplate itemTemplate = itemTemplateMapper.mapFillItemTemplateDtoToItemTemplate(dto);
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (itemTemplateRepository.findById(uuid).isPresent());
        itemTemplate.setId(uuid);
        itemTemplateRepository.save(itemTemplate);
        return itemTemplateMapper.mapItemTemplateToGetItemTemplateDto(itemTemplate);
    }

    public List<GetItemTemplateDto> getCategories() {
        List<GetItemTemplateDto> listOfGetItemTemplateDto = new ArrayList<>();
        Iterable<ItemTemplate> categories = itemTemplateRepository.findAll();
        for (ItemTemplate itemTemplate : categories) {
            listOfGetItemTemplateDto.add(itemTemplateMapper.mapItemTemplateToGetItemTemplateDto(itemTemplate));
        }
        return listOfGetItemTemplateDto;
    }
}
