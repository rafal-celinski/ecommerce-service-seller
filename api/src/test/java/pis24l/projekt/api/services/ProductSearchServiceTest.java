package pis24l.projekt.api.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pis24l.projekt.api.model.Product;
import pis24l.projekt.api.repositories.ProductRepository;
import pis24l.projekt.api.service.ProductSearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductSearchServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ProductSearchService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        productService.setEntityManager(entityManager); // Manually inject the mock EntityManager
    }

    private void setupMockQuery(List<Product> productList, long total) {
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Product> criteriaQuery = mock(CriteriaQuery.class);
        Root<Product> root = mock(Root.class);
        TypedQuery<Product> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Product.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Product.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(productList);

        // Ensure method chaining by returning the same mock for setFirstResult and setMaxResults
        when(typedQuery.setFirstResult(anyInt())).thenReturn(typedQuery);
        when(typedQuery.setMaxResults(anyInt())).thenReturn(typedQuery);

        CriteriaQuery<Long> countQuery = mock(CriteriaQuery.class);
        Root<Product> countRoot = mock(Root.class);
        TypedQuery<Long> countTypedQuery = mock(TypedQuery.class);

        when(criteriaBuilder.createQuery(Long.class)).thenReturn(countQuery);
        when(countQuery.from(Product.class)).thenReturn(countRoot);
        when(countQuery.select(any())).thenReturn(countQuery); // Mocking the select method
        when(countQuery.where(any(Predicate[].class))).thenReturn(countQuery); // Mocking the where method

        when(countTypedQuery.getSingleResult()).thenReturn(total);
        when(entityManager.createQuery(countQuery)).thenReturn(countTypedQuery);
    }


    @Test
    public void testSearchProducts_withAllParameters() {
        // Given
        BigDecimal minPrice = BigDecimal.valueOf(0);
        BigDecimal maxPrice = BigDecimal.valueOf(100);
        String search = "searchTerm";
        Long category = 1L;
        Long subcategory = 2L;
        String location = "location";
        Pageable pageable = PageRequest.of(0, 10);

        List<Product> productList = new ArrayList<>();
        Product product = new Product("xd", BigDecimal.valueOf(20), "Warszawa", LocalDateTime.now(), "xd", 0L, 0L);
        productList.add(product);
        long total = 1;

        setupMockQuery(productList, total);

        // When
        Page<Product> result = productService.searchProducts(search, category, subcategory, minPrice, maxPrice, location, pageable);

        // Then
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
    }

    @Test
    public void testSearchProducts_withoutCategory() {
        // Given
        BigDecimal minPrice = BigDecimal.valueOf(0);
        BigDecimal maxPrice = BigDecimal.valueOf(100);
        String search = "searchTerm";
        Long category = null;
        Long subcategory = 2L;
        String location = "location";
        Pageable pageable = PageRequest.of(0, 10);

        List<Product> productList = new ArrayList<>();
        Product product = new Product("xd", BigDecimal.valueOf(20), "Warszawa", LocalDateTime.now(), "xd", 0L, 0L);
        productList.add(product);
        long total = 1;

        setupMockQuery(productList, total);

        // When
        Page<Product> result = productService.searchProducts(search, category, subcategory, minPrice, maxPrice, location, pageable);

        // Then
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
    }

    @Test
    public void testSearchProducts_withoutSubcategory() {
        // Given
        BigDecimal minPrice = BigDecimal.valueOf(0);
        BigDecimal maxPrice = BigDecimal.valueOf(100);
        String search = "searchTerm";
        Long category = 1L;
        Long subcategory = null;
        String location = "location";
        Pageable pageable = PageRequest.of(0, 10);

        List<Product> productList = new ArrayList<>();
        Product product = new Product("xd", BigDecimal.valueOf(20), "Warszawa", LocalDateTime.now(), "xd", 0L, 0L);
        productList.add(product);
        long total = 1;

        setupMockQuery(productList, total);

        // When
        Page<Product> result = productService.searchProducts(search, category, subcategory, minPrice, maxPrice, location, pageable);

        // Then
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
    }

    @Test
    public void testSearchProducts_withoutPriceRange() {
        // Given
        BigDecimal minPrice = null;
        BigDecimal maxPrice = null;
        String search = "searchTerm";
        Long category = 1L;
        Long subcategory = 2L;
        String location = "location";
        Pageable pageable = PageRequest.of(0, 10);

        List<Product> productList = new ArrayList<>();
        Product product = new Product("xd", BigDecimal.valueOf(20), "Warszawa", LocalDateTime.now(), "xd", 0L, 0L);
        productList.add(product);
        long total = 1;

        setupMockQuery(productList, total);

        // When
        Page<Product> result = productService.searchProducts(search, category, subcategory, minPrice, maxPrice, location, pageable);

        // Then
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
    }

    @Test
    public void testSearchProducts_withoutLocation() {
        // Given
        BigDecimal minPrice = BigDecimal.valueOf(0);
        BigDecimal maxPrice = BigDecimal.valueOf(100);
        String search = "searchTerm";
        Long category = 1L;
        Long subcategory = 2L;
        String location = null;
        Pageable pageable = PageRequest.of(0, 10);

        List<Product> productList = new ArrayList<>();
        Product product = new Product("xd", BigDecimal.valueOf(20), "Warszawa", LocalDateTime.now(), "xd", 0L, 0L);
        productList.add(product);
        long total = 1;

        setupMockQuery(productList, total);

        // When
        Page<Product> result = productService.searchProducts(search, category, subcategory, minPrice, maxPrice, location, pageable);

        // Then
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
    }

    @Test
    public void testSearchProducts_noCriteria() {
        // Given
        String search = "";
        Long category = null;
        Long subcategory = null;
        BigDecimal minPrice = null;
        BigDecimal maxPrice = null;
        String location = "";
        Pageable pageable = PageRequest.of(0, 10);

        List<Product> productList = new ArrayList<>();
        Product product = new Product("xd", BigDecimal.valueOf(20), "Warszawa", LocalDateTime.now(), "xd", 0L, 0L);
        productList.add(product);
        long total = 1;

        setupMockQuery(productList, total);

        // When
        Page<Product> result = productService.searchProducts(search, category, subcategory, minPrice, maxPrice, location, pageable);

        // Then
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
    }

    @Test
    public void testSearchProducts_withPagination() {
        // Given
        String search = "searchTerm";
        Long category = 1L;
        Long subcategory = 2L;
        BigDecimal minPrice = BigDecimal.valueOf(0);
        BigDecimal maxPrice = BigDecimal.valueOf(100);
        String location = "location";
        Pageable pageable = PageRequest.of(0, 10);

        List<Product> productList = new ArrayList<>();
        Product product = new Product("xd", BigDecimal.valueOf(20), "Warszawa", LocalDateTime.now(), "xd", 0L, 0L);
        productList.add(product);
        long total = 1;

        setupMockQuery(productList, total);

        // When
        Page<Product> result = productService.searchProducts(search, category, subcategory, minPrice, maxPrice, location, pageable);

        // Then
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
    }
}
