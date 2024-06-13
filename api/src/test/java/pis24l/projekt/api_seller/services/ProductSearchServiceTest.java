package pis24l.projekt.api_seller.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import pis24l.projekt.api_seller.models.Product;
import pis24l.projekt.api_seller.models.ProductStatus;
import pis24l.projekt.api_seller.repositories.mongo.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ProductSearchServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private ProductSearchService productSearchService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private void setupMockQuery(List<Product> productList, long total) {
        when(mongoTemplate.find(any(Query.class), eq(Product.class))).thenReturn(productList);
        when(mongoTemplate.count(any(Query.class), eq(Product.class))).thenReturn(total);
    }

    @Test
    public void testSearchProducts_withVariousParameters() {
        // Common parameters
        String search = "searchTerm";
        String category = "category";
        String subcategory = "subcategory";
        String location = "location";
        Pageable pageable = PageRequest.of(0, 10);

        List<Product> productList = new ArrayList<>();
        Product product = new Product("Product 1", BigDecimal.valueOf(20), "Location 1", category, subcategory, location, ProductStatus.UP);
        productList.add(product);
        long total = 1;

        setupMockQuery(productList, total);

        // When
        Page<Product> resultWithAllParams = productSearchService.searchProducts(search, category, subcategory, location,ProductStatus.UP, pageable);

        // Then
        assertEquals(1, resultWithAllParams.getTotalElements());
        assertEquals(1, resultWithAllParams.getContent().size());
    }


    @Test
    public void testGetProductById_withImages() {
        // Mock data
        String productId = "1";
        Product product = new Product(productId, "Product 1", BigDecimal.valueOf(20));

        // Mock behavior
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(mongoTemplate.find(any(Query.class), eq(String.class))).thenReturn(Collections.singletonList("image-url"));

        // Call the method
        Product result = productSearchService.getProductById(productId);

        // Verify
        assertNotNull(result);
        assertEquals(productId, result.getId());
    }

    @Test
    public void testGetProductById_notFound() {
        // Mock data
        when(productRepository.findById("1")).thenReturn(Optional.empty());

        // Test & Verify
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productSearchService.getProductById("1");
        });
        assertEquals("Product not found with id 1", exception.getMessage());

        verify(productRepository, times(1)).findById("1");
    }
}
