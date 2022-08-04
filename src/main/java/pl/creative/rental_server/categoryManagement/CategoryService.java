package pl.creative.rental_server.categoryManagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.creative.rental_server.categoryManagement.dto.CategoryMapper;
import pl.creative.rental_server.categoryManagement.dto.FillCategoryDto;
import pl.creative.rental_server.categoryManagement.dto.GetCategoryDto;
import pl.creative.rental_server.entities.Category;
import pl.creative.rental_server.handlers.RandomIdHandler;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;
import pl.creative.rental_server.itemManagement.dto.ItemMapper;
import pl.creative.rental_server.repository.CategoryRepository;
import pl.creative.rental_server.exception.notFound.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final RandomIdHandler randomIdHandler;
    private final ItemMapper itemMapper;

    @Transactional
    public GetCategoryDto addItem(FillCategoryDto fillCategoryDto) {
        Category categoryToSave = categoryMapper.mapFillCategoryDtoToCategory(fillCategoryDto);
        String uuid = randomIdHandler.generateUniqueIdFromTable(categoryRepository);
        categoryToSave.setId(uuid);
        Category savedCategory = categoryRepository.save(categoryToSave);
        log.info("Created and saved category {}", savedCategory);
        return categoryMapper.mapCategoryToGetCategoryDto(savedCategory);
    }

    public GetCategoryDto getCategoryById(String id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::mapCategoryToGetCategoryDto)
                .orElseThrow(() -> {
                    log.error("Category with id {} is not exists", id);
                    return new NotFoundException("Type of the item with id " + id + " is not exists");
                });
    }

    public List<GetCategoryDto> getTypesOfItem() {
        List<GetCategoryDto> listOfGetCategoryDto = new ArrayList<>();
        Iterable<Category> listOfCategory = categoryRepository.findAll();
        for (Category category : listOfCategory) {
            listOfGetCategoryDto.add(categoryMapper.mapCategoryToGetCategoryDto(category));
        }
        return listOfGetCategoryDto;
    }

    public List<GetItemDto> getItemsByCategoryId(String id) {
        return categoryRepository.findById(id).map(Category::getItems)
                .orElseThrow(() -> new NotFoundException("Category with id " + id + " is not exists"))
                .stream()
                .map(itemMapper::mapItemToGetItemDto)
                .toList();
    }

}
