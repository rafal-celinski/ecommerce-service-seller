package pis24l.projekt.api_seller.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pis24l.projekt.api_seller.models.Category;
import pis24l.projekt.api_seller.repositories.mongo.CategoryRepository;
import pis24l.projekt.api_seller.services.CategorySearchService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CategorySearchServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategorySearchService categorySearchService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllCategories() {
        // Given
        List<Category> categoryList = new ArrayList<>();
        Category category = new Category("1", "test");
        categoryList.add(category);

        when(categoryRepository.findAll()).thenReturn(categoryList);

        // When
        List<Category> result = categorySearchService.findAll();

        // Then
        assertEquals(1, result.size());
        assertEquals("test", result.get(0).getName());
    }

    @Test
    public void testFindById_whenCategoryExists() {
        // Given
        String categoryId = "1";
        Category category = new Category(categoryId, "test");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // When
        Optional<Category> result = categorySearchService.findById(categoryId);

        // Then
        assertTrue(result.isPresent());
        assertEquals(categoryId, result.get().getId());
        assertEquals("test", result.get().getName());
    }

    @Test
    public void testFindById_whenCategoryDoesNotExist() {
        // Given
        String categoryId = "1";

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // When
        Optional<Category> result = categorySearchService.findById(categoryId);

        // Then
        assertTrue(result.isEmpty());
    }
}
