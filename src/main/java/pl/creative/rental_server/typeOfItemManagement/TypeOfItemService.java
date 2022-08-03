package pl.creative.rental_server.typeOfItemManagement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.creative.rental_server.Entities.TypeOfItem;
import pl.creative.rental_server.Handlers.RandomIdHandler;
import pl.creative.rental_server.ItemManagement.dto.GetItemDto;
import pl.creative.rental_server.ItemManagement.dto.ItemMapper;
import pl.creative.rental_server.Repository.TypeOfItemRepository;
import pl.creative.rental_server.exception.NotFoundException;
import pl.creative.rental_server.typeOfItemManagement.dto.FillTypeOfItemDto;
import pl.creative.rental_server.typeOfItemManagement.dto.GetTypeOfItemDto;
import pl.creative.rental_server.typeOfItemManagement.dto.TypeOfItemMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeOfItemService {
    private final TypeOfItemMapper typeOfItemMapper;
    private final TypeOfItemRepository typeOfItemRepository;
    private final RandomIdHandler randomIdHandler;
    private final ItemMapper itemMapper;

    @Transactional
    public GetTypeOfItemDto addItem(FillTypeOfItemDto fillTypeOfItemDto) {
        TypeOfItem typeOfItemToSave = typeOfItemMapper.mapFillTypeOfItemDtoToTypeOfItem(fillTypeOfItemDto);
        String uuid = randomIdHandler.generateUniqueIdFromTable(typeOfItemRepository);
        typeOfItemToSave.setId(uuid);
        TypeOfItem savedTypeOfItem = typeOfItemRepository.save(typeOfItemToSave);
        return typeOfItemMapper.mapTypeOfItemToGetTypeOfItemDto(savedTypeOfItem);
    }

    public GetTypeOfItemDto getTypeOfItemById(String id) {
        return typeOfItemRepository.findById(id).map(typeOfItemMapper::mapTypeOfItemToGetTypeOfItemDto)
                .orElseThrow(() -> new NotFoundException("Type of the item with id " + id + " is not exists"));
    }

    public List<GetTypeOfItemDto> getTypesOfItem() {
        List<GetTypeOfItemDto> listOfGetTypeOfItemDto = new ArrayList<>();
        Iterable<TypeOfItem> listOfTypeOfItem = typeOfItemRepository.findAll();
        for (TypeOfItem typeOfItem : listOfTypeOfItem) {
            listOfGetTypeOfItemDto.add(typeOfItemMapper.mapTypeOfItemToGetTypeOfItemDto(typeOfItem));
        }
        return listOfGetTypeOfItemDto;
    }

    public List<GetItemDto> getItemsByTypeOfItemId(String id) {
        return typeOfItemRepository.findById(id).map(TypeOfItem::getItems)
                .orElseThrow(() -> new NotFoundException("Category with id " + id + " is not exists"))
                .stream()
                .map(itemMapper::mapItemToGetItemDto)
                .toList();
    }

}
