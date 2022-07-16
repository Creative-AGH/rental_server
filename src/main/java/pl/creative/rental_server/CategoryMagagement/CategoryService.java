package pl.creative.rental_server.CategoryMagagement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.creative.rental_server.CategoryMagagement.dto.CategoryMapper;
import pl.creative.rental_server.CategoryMagagement.dto.FillCategoryDto;
import pl.creative.rental_server.CategoryMagagement.dto.GetCategoryDto;
import pl.creative.rental_server.Entities.Category;
import pl.creative.rental_server.Repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public GetCategoryDto addCategory(FillCategoryDto dto) {
        Category category = categoryMapper.mapFillCategoryDtoToCategory(dto);
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (categoryRepository.findById(uuid).isPresent());
        category.setId(uuid);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.mapCategoryToGetCategoryDto(savedCategory);
    }

    public List<GetCategoryDto> getCategories() {
        List<GetCategoryDto> listOfGetCategoryDto = new ArrayList<>();
        Iterable<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            listOfGetCategoryDto.add(categoryMapper.mapCategoryToGetCategoryDto(category));
        }
        return listOfGetCategoryDto;
    }
}
