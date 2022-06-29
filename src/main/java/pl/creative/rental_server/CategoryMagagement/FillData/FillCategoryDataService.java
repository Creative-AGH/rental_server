package pl.creative.rental_server.CategoryMagagement.FillData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.creative.rental_server.Entities.Category;
import pl.creative.rental_server.Repository.CategoryRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FillCategoryDataService {
    private final CategoryRepository categoryRepository;
    private final FillCategoryMapper fillCategoryMapper;
    @Transactional
    public void addCategory(FillCategoryDto dto){
        Category category = fillCategoryMapper.mapCategoryDtoToCategory(dto);

        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (categoryRepository.findById(uuid).isPresent());
        category.setId(uuid);

        categoryRepository.save(category);
    }


}
