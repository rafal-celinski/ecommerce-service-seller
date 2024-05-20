package pis24l.projekt.api.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pis24l.projekt.api.model.Product;
import pis24l.projekt.api.repositories.ProductRepository;
import pis24l.projekt.api.service.ProductSearchService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
        productService.setEntityManager(entityManager);
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
        Boolean hasToPass = true;

        List<Product> productList = new ArrayList<>();
        Product product = new Product("xd", BigDecimal.valueOf(20), "Warszawa", LocalDateTime.now(), "xd", 0L, 0L);
        productList.add(product);

        // Mocking the behavior of the dynamic query
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<Product> criteriaQuery = mock(CriteriaQuery.class);
        Root<Product> root = mock(Root.class);
        TypedQuery<Product> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Product.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Product.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(productList);

        // When
        List<Product> result = productService.searchProducts(search, category, subcategory, minPrice, maxPrice, location, hasToPass);

        // Then
        assertEquals(1, result.size());
        // Add more assertions as needed
    }
}
