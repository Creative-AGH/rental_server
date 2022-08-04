package pl.creative.rental_server.itemTemplateManagement.dto;

import org.mapstruct.Mapper;
import pl.creative.rental_server.entities.ItemTemplate;

@Mapper(componentModel = "spring", uses = ItemTemplateMapper.class)
public interface ItemTemplateMapper {

    ItemTemplate mapFillItemTemplateDtoToItemTemplate(FillItemTemplateDto fillItemTemplateDto);

    GetItemTemplateDto mapItemTemplateToGetItemTemplateDto(ItemTemplate itemTemplate);

}
