package pl.creative.rental_server.itemTemplateManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.itemTemplateManagement.dto.ItemTemplateMapper;
import pl.creative.rental_server.itemTemplateManagement.dto.FillItemTemplateDto;
import pl.creative.rental_server.itemTemplateManagement.dto.GetItemTemplateDto;
import pl.creative.rental_server.entities.ItemTemplate;
import pl.creative.rental_server.repository.ItemTemplateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemTemplateService {
    private final ItemTemplateRepository itemTemplateRepository;
    private final ItemTemplateMapper itemTemplateMapper;


    public GetItemTemplateDto addItemTemplate(FillItemTemplateDto dto) {
        ItemTemplate itemTemplate = itemTemplateMapper.mapFillItemTemplateDtoToItemTemplate(dto);
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (itemTemplateRepository.findById(uuid).isPresent());
        itemTemplate.setId(uuid);
        itemTemplateRepository.save(itemTemplate);
        return itemTemplateMapper.mapItemTemplateToGetItemTemplateDto(itemTemplate);
    }

    public List<GetItemTemplateDto> getItemTemplates() {
        List<GetItemTemplateDto> listOfGetItemTemplateDto = new ArrayList<>();
        Iterable<ItemTemplate> categories = itemTemplateRepository.findAll();
        for (ItemTemplate itemTemplate : categories) {
            listOfGetItemTemplateDto.add(itemTemplateMapper.mapItemTemplateToGetItemTemplateDto(itemTemplate));
        }
        return listOfGetItemTemplateDto;
    }
}
