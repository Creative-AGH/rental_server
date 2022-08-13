package pl.creative.rental_server.categoryManagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.creative.rental_server.categoryManagement.dto.CategoryMapper;
import pl.creative.rental_server.categoryManagement.dto.FillCategoryDto;
import pl.creative.rental_server.categoryManagement.dto.GetCategoryDto;
import pl.creative.rental_server.entities.Category;
import pl.creative.rental_server.exception.notFound.CategoryException;
import pl.creative.rental_server.exception.notFound.CategoryNotFound;
import pl.creative.rental_server.handlers.RandomIdHandler;
import pl.creative.rental_server.itemManagement.dto.GetItemDto;
import pl.creative.rental_server.itemManagement.dto.ItemMapper;
import pl.creative.rental_server.repository.CategoryRepository;
import pl.creative.rental_server.exception.notFound.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final RandomIdHandler randomIdHandler;
    private final ItemMapper itemMapper;

    @Transactional()
    public GetCategoryDto addCategory(FillCategoryDto fillCategoryDto) {
        log.info("Creating new category");
        String categoryName = fillCategoryDto.getCategoryName();
        Optional<Category> optionalCategory = categoryRepository.findCategoryByCategoryName(categoryName);
        if(optionalCategory.isEmpty()){
            Category categoryToSave = categoryMapper.mapFillCategoryDtoToCategory(fillCategoryDto);
            String uuid = randomIdHandler.generateUniqueIdFromTable(categoryRepository);
            categoryToSave.setId(uuid);
            Category savedCategory = categoryRepository.save(categoryToSave);
            log.info("Successfully created and saved category");
            return categoryMapper.mapCategoryToGetCategoryDto(savedCategory);
        }else {
            log.error("Category with that category name {} already exists", categoryName);
            throw new CategoryException(String.format("Category with that category name %s already exists", categoryName));
        }

    }

    public GetCategoryDto getCategoryById(String categoryId) {
        log.info("Getting the category by id");
        return categoryRepository.findById(categoryId)
                .map(categoryMapper::mapCategoryToGetCategoryDto)
                .orElseThrow(() -> {
                    log.error("Category with that id {} does not exists", categoryId);
                    return new CategoryNotFound(String.format("Category with that id %s does not exists", categoryId));
                });
    }

    public List<GetCategoryDto> getAllCategories() {
        log.info("Getting all categories");
        List<GetCategoryDto> listOfGetCategoryDto = new ArrayList<>();
        Iterable<Category> listOfCategory = categoryRepository.findAll();
        for (Category category : listOfCategory) {
            listOfGetCategoryDto.add(categoryMapper.mapCategoryToGetCategoryDto(category));
        }
        return listOfGetCategoryDto;
    }

    public List<GetItemDto> getAllItemsByCategoryId(String categoryId) {
        log.info("Getting the items by category id");
        return categoryRepository.findById(categoryId)
                .map(Category::getItems)
                .orElseThrow(() -> {
                    log.error("Category with that id {} does not exists", categoryId);
                    return new CategoryNotFound(String.format("Category with that id %s does not exists", categoryId));
                })
                .stream()
                .map(itemMapper::mapItemToGetItemDto)
                .toList();
    }

}
