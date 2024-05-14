package pis24l.projekt.api.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pis24l.projekt.api.model.Product;
import pis24l.projekt.api.repositories.ProductRepository;
import pis24l.projekt.api.service.ProductSearchService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductSearchServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductSearchService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchProducts() {
        // Given
        BigDecimal minPrice = BigDecimal.valueOf(0);
        BigDecimal maxPrice = BigDecimal.valueOf(100);
        String search = "searchTerm";
        Long category = 1L;
        Long subcategory = 2L;
        String location = "location";
        Boolean hasToPass = false;

        List<Product> productList = new ArrayList<>();
        Product product = new Product("xd",BigDecimal.valueOf(20), "Warszawa", LocalDateTime.now(),"xd", 0L,0L);
        productList.add(product);

        when(productRepository.findByPriceBetweenAndTitleContainingAndCategoryAndSubcategoryAndLocation(
                minPrice, maxPrice, search, category, subcategory, location))
                .thenReturn(productList);

        // When
        List<Product> result = productService.searchProducts(search,category,subcategory,minPrice,maxPrice,location,true);

        // Then
        assertEquals(1, result.size());
        // Add more assertions as needed
    }
}
