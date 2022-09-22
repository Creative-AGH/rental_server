package pl.creative.rental_server.core.features.moderator.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.creative.rental_server.core.features.moderator.category.dto.CategoryMapper;
import pl.creative.rental_server.core.features.moderator.category.dto.FillCategoryDto;
import pl.creative.rental_server.core.features.moderator.category.dto.GetCategoryDto;
import pl.creative.rental_server.core.features.moderator.item.dto.ItemMapper;
import pl.creative.rental_server.core.global.exception.notFound.CategoryException;
import pl.creative.rental_server.core.global.exception.notFound.CategoryNotFound;
import pl.creative.rental_server.core.global.handlersAndUtils.RandomIdHandler;
import pl.creative.rental_server.db.entities.Category;
import pl.creative.rental_server.db.repository.CategoryRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;
    @Mock
    private RandomIdHandler randomIdHandler;
    @Mock
    private ItemMapper itemMapper;
    private CategoryService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CategoryService(categoryMapper, categoryRepository, randomIdHandler, itemMapper);
    }

    @Test
    void addCategory_shouldAddGivenCategory() {
        // given
        FillCategoryDto fillCategoryDto = new FillCategoryDto("categoryName", "description");
        Category category = new Category("categoryId", "categoryName", "description");
        given(categoryRepository.findCategoryByCategoryName(anyString())).willReturn(Optional.empty());
        given(categoryMapper.mapFillCategoryDtoToCategory(fillCategoryDto)).willReturn(category);
        // when
        underTest.addCategory(fillCategoryDto);
        // then
        ArgumentCaptor<Category> studentArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository).save(studentArgumentCaptor.capture());
        Category capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent.getClass()).isEqualTo(Category.class);
        assertThat(capturedStudent).isEqualTo(category);
        assertThat(capturedStudent.equals(category)).isTrue();
    }

    @Test
    void addCategory_shouldThrowCategoryException_shouldNotAddGivenCategory() {
        // given
        String categoryName = "categoryName";
        FillCategoryDto fillCategoryDto = new FillCategoryDto(categoryName, "description");
        Category category = new Category("categoryId", categoryName, "description");
        given(categoryRepository.findCategoryByCategoryName(anyString())).willReturn(Optional.of(category)); //the category already exists
//        given(categoryMapper.mapFillCategoryDtoToCategory(fillCategoryDto)).willReturn(category); //this line is unnecessary!
        // when
        // then
        assertThatThrownBy(() -> underTest.addCategory(fillCategoryDto))
                .isInstanceOf(CategoryException.class)
                .hasMessage(String.format("Category with that category name %s already exists", categoryName));
        verify(categoryRepository, never()).save(any()); //never saves any category
    }


    @Test
    void getCategoryById_shouldReturnGetCategoryDto() {
        //given
        String categoryId = "categoryId";
        String categoryName = "categoryName";
        Category category = new Category("categoryId", categoryName, "description");
        GetCategoryDto getCategoryDto = new GetCategoryDto("categoryId", categoryName, "description");
        given(categoryRepository.findById(anyString())).willReturn(Optional.of(category));
        given(categoryMapper.mapCategoryToGetCategoryDto(any())).willReturn(getCategoryDto);
        //when
        GetCategoryDto returnedGetCategoryDto = underTest.getCategoryById(categoryId);
        //then
        verify(categoryRepository).findById(categoryId); //checking if repo invoke findById with proper categoryId
        assertThat(returnedGetCategoryDto).isEqualTo(getCategoryDto);
    }

    @Test
    void getCategoryById_shouldThrowCategoryNotFound() {
        //given
        String categoryId = "categoryId";
        given(categoryRepository.findById(anyString())).willReturn(Optional.empty());
        //when
        //then
        assertThatThrownBy(() -> underTest.getCategoryById(categoryId))
                .isInstanceOf(CategoryNotFound.class)
                .hasMessage("Category with that id %s does not exist", categoryId);
    }

    @Test
    void getAllCategories_shouldReturnListOfGetCategoryDto() {
        //given
        //when
        underTest.getAllCategories();
        //then
        verify(categoryRepository).findAll();
    }

    @Test
    void getAllItemsByCategoryId_shouldThrowCategoryNotFound() {
        //given
        String categoryId = "categoryId";
        given(categoryRepository.findById(categoryId)).willReturn(Optional.empty());
        //when
        //then
        assertThatThrownBy(() -> underTest.getAllItemsByCategoryId(categoryId))
                .isInstanceOf(CategoryNotFound.class)
                .hasMessage(String.format("Category with that id %s does not exist", categoryId));
        verify(itemMapper, never()).mapItemToGetItemDto(any());
    }
}