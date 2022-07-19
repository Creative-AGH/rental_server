package pl.creative.rental_server.CategoryMagagement.dto;

import org.mapstruct.Mapper;
import pl.creative.rental_server.Entities.ItemTemplate;

@Mapper(componentModel = "spring", uses = ItemTemplateMapper.class)
public interface ItemTemplateMapper {

    ItemTemplate mapFillItemTemplateDtoToItemTemplate(FillItemTemplateDto fillItemTemplateDto);

    GetItemTemplateDto mapItemTemplateToGetItemTemplateDto(ItemTemplate itemTemplate);

}
