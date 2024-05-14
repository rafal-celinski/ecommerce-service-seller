package pis24l.projekt.api.controllers;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pis24l.projekt.api.model.Product;
import pis24l.projekt.api.service.ProductSearchService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductSearchControllerTest {
    @Mock
    private ProductSearchService productService;

    @InjectMocks
    private ProductSearchController productSearchController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchProducts() {
        // Given
        String search = "searchTerm";
        BigDecimal minPrice = BigDecimal.valueOf(10);
        BigDecimal maxPrice = BigDecimal.valueOf(100);
        String location = "Location";

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", BigDecimal.valueOf(50), location, LocalDateTime.now(),"xd",0L,0L));
        products.add(new Product("Product 2", BigDecimal.valueOf(80), location, LocalDateTime.now(),"xd",0L,0L));

        when(productService.searchProducts(search, null, null, minPrice, maxPrice, location, false)).thenReturn(products);

        // When
        List<Product> result = productSearchController.searchProducts(search, null, null, minPrice, maxPrice, location, false);

        // Then
        assertEquals(products.size(), result.size());
        // Add more assertions as needed
    }
}
