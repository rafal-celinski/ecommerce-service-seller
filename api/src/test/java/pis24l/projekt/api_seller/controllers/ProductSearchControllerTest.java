package pis24l.projekt.api_seller.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pis24l.projekt.api_seller.models.Product;
import pis24l.projekt.api_seller.models.ProductStatus;
import pis24l.projekt.api_seller.services.ProductSearchService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class ProductSearchControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductSearchService productSearchService;

    @InjectMocks
    private ProductSearchController productSearchController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productSearchController).build();
    }

    @Test
    public void testSearchProducts_withAllParameters() {
        // Given
        BigDecimal minPrice = BigDecimal.valueOf(0);
        BigDecimal maxPrice = BigDecimal.valueOf(100);
        String search = "searchTerm";
        String category = "dg";
        String subcategory = "asjnf";
        String location = "location";
        Pageable pageable = PageRequest.of(0, 10);

        List<Product> productList = new ArrayList<>();
        Product product = new Product("1", "Product 1", BigDecimal.valueOf(20), "Warsaw", category, subcategory, "description", ProductStatus.UP);
        productList.add(product);
        long total = 1;

        when(productSearchService.searchProducts(anyString(), anyString(), anyString(), anyString(), eq(ProductStatus.UP), eq(pageable)))
                .thenReturn(new PageImpl<>(productList, pageable, total));

        // When
        ResponseEntity<Page<Product>> response = productSearchController.searchProducts(search, category, subcategory, location, ProductStatus.UP, pageable);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getTotalElements());
        assertEquals(1, response.getBody().getContent().size());
    }

    @Test
    public void testGetProductById() throws Exception {
        // Given
        String productId = "1";
        Product product = new Product(productId, "Mock Product", BigDecimal.valueOf(10.99));

        when(productSearchService.getProductById(productId)).thenReturn(product);

        // When
        ResponseEntity<Product> response = productSearchController.getProductById(productId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productId, response.getBody().getId());
        assertEquals("Mock Product", response.getBody().getTitle());
        assertEquals(BigDecimal.valueOf(10.99), response.getBody().getPrice());
    }

    @Test
    public void testListAllProducts() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<Product> productList = new ArrayList<>();
        Product product = new Product("1", "Product 1", BigDecimal.valueOf(20), "Warsaw", "category", "subcategory", "description", ProductStatus.UP);
        productList.add(product);
        Page<Product> productPage = new PageImpl<>(productList, pageable, productList.size());

        when(productSearchService.listAllProducts(pageable)).thenReturn(productPage);

        // When
        ResponseEntity<Page<Product>> response = productSearchController.listAllProducts(pageable);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getTotalElements());
        assertEquals("Product 1", response.getBody().getContent().get(0).getTitle());
    }

    @Test
    public void testListProductsInProgress() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<Product> productList = new ArrayList<>();
        Product product = new Product("1", "Product 1", BigDecimal.valueOf(20), "Warsaw", "category", "subcategory", "description", ProductStatus.UP);
        productList.add(product);
        Page<Product> productPage = new PageImpl<>(productList, pageable, productList.size());

        when(productSearchService.listProductsInProgress(pageable)).thenReturn(productPage);

        // When
        ResponseEntity<Page<Product>> response = productSearchController.listProductsInProgress(pageable);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getTotalElements());
        assertEquals("Product 1", response.getBody().getContent().get(0).getTitle());
    }
}
