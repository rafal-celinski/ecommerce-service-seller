package pis24l.projekt.api_seller.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pis24l.projekt.api_seller.model.Category;
import pis24l.projekt.api_seller.model.Subcategory;
import pis24l.projekt.api_seller.repositories.mongo.SubcategoryRepository;
import pis24l.projekt.api_seller.service.SubcategorySearchService;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SubcategorySearchServiceTest {

    @Mock
    private SubcategoryRepository productSubcategoryRepository;

    @InjectMocks
    private SubcategorySearchService productSubcategorySearchService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchAllProductSubcategories() {
        // Given
        String search = "searchTerm";

        List<Subcategory> subcategoryList = new ArrayList<>();
        Subcategory subcategory = new Subcategory();
        subcategoryList.add(subcategory);

        when(productSubcategoryRepository.findAll()).thenReturn(subcategoryList);

        // When
        List<Subcategory> result = productSubcategorySearchService.getAllSubcategories();

        // Then
        assertEquals(1, result.size());
        // Add more assertions as needed
    }

    @Test
    public void testSearchCategorySubcategories() {
        // Given
        List<Subcategory> subcategoryList = new ArrayList<>();
        Long category = 0L;
        Subcategory subcategory = new Subcategory(0L,"test",new Category(0L, "test2"));
        subcategoryList.add(subcategory);

        when(productSubcategoryRepository.findSubcategoriesByCategoryId(category)).thenReturn(subcategoryList);

        // When
        List<Subcategory> result = productSubcategorySearchService.getSubcategoriesByCategoryId(category);

        // Then
        assertEquals(1, result.size());
        assertEquals("test", result.get(0).getName());
    }
}
