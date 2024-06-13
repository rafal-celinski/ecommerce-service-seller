package pis24l.projekt.api_seller.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import pis24l.projekt.api_seller.models.Category;
import pis24l.projekt.api_seller.models.Subcategory;
import pis24l.projekt.api_seller.services.CategorySearchService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CategorySearchControllerTest {

    @Mock
    private CategorySearchService categorySearchService;

    @InjectMocks
    private CategorySearchController categorySearchController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCategories() {
        // Given
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("1", "Category 1"));
        categories.add(new Category("2", "Category 2"));

        when(categorySearchService.findAll()).thenReturn(categories);

        // When
        List<Category> result = categorySearchController.getAllCategories();

        // Then
        assertEquals(categories.size(), result.size());
        assertEquals(categories.get(0).getName(), result.get(0).getName());
        assertEquals(categories.get(1).getName(), result.get(1).getName());
    }

    @Test
    public void testGetSubcategoriesByCategoryId_whenCategoryExists() {
        // Given
        List<Subcategory> subcategories = new ArrayList<>();
        subcategories.add(new Subcategory("Subcategory 1"));
        subcategories.add(new Subcategory("Subcategory 2"));
        Category category = new Category("1", "Category 1");
        category.setSubcategories(subcategories);

        when(categorySearchService.findById("1")).thenReturn(Optional.of(category));

        // When
        ResponseEntity<List<Subcategory>> response = categorySearchController.getSubcategoriesByCategoryId("1");

        // Then
        assertEquals(ResponseEntity.ok(subcategories), response);
        assertTrue(response.getBody().containsAll(subcategories));
    }

    @Test
    public void testGetSubcategoriesByCategoryId_whenCategoryDoesNotExist() {
        // Given
        when(categorySearchService.findById("1")).thenReturn(Optional.empty());

        // When
        ResponseEntity<List<Subcategory>> response = categorySearchController.getSubcategoriesByCategoryId("1");

        // Then
        assertEquals(ResponseEntity.notFound().build(), response);
    }
}
