package pis24l.projekt.api.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pis24l.projekt.api.model.Category;
import pis24l.projekt.api.model.Subcategory;
import pis24l.projekt.api.service.SubcategorySearchService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SubcategorySearchControllerTest {

    @Mock
    private SubcategorySearchService productSubcategorySearchService;

    @InjectMocks
    private SubcategorySearchController productSubcategorySearchController;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchAllProductSubcategories() {
        // Given
        List<Subcategory> subcategoryList = new ArrayList<>();
        Subcategory subcategory = new Subcategory(0L,"test",new Category(0L,"test2"));
        Subcategory subcategory2 = new Subcategory(0L,"test",new Category(1L,"test2"));
        subcategoryList.add(subcategory);
        subcategoryList.add(subcategory2);

        when(productSubcategorySearchService.getAllSubcategories()).thenReturn(subcategoryList);

        // When
        List<Subcategory> result = productSubcategorySearchController.getAllSubcategories();

        // Then
        assertEquals(2, result.size());
        // Add more assertions as needed
    }

    @Test
    public void testSearchCategorySubcategories() {
        // Given
        List<Subcategory> subcategoryList = new ArrayList<>();
        Subcategory subcategory = new Subcategory(0L,"test",new Category(0L,"test2"));
        Subcategory subcategory2 = new Subcategory(0L,"test",new Category(1L,"test2"));
        subcategoryList.add(subcategory2);

        Long category = 0L;

        when(productSubcategorySearchService.getSubcategoriesByCategoryId(category)).thenReturn(subcategoryList);

        // When
        List<Subcategory> result = productSubcategorySearchController.getSubcategoriesByCategoryId(category);

        // Then
        assertEquals(1, result.size());
        // Add more assertions as needed
    }
}
