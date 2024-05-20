package pis24l.projekt.api.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pis24l.projekt.api.model.Product;
import pis24l.projekt.api.service.ProductSearchService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ProductSearchControllerTest {

    private MockMvc mockMvc;


    @Mock
    private ProductSearchService productService;

    @InjectMocks
    private ProductSearchController productSearchController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productSearchController).build();
    }



    @Test
    public void testSearchProducts_withAllParameters() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);

        List<Product> productList = new ArrayList<>();
        Product product = new Product("title", BigDecimal.valueOf(20), "Warszawa", LocalDateTime.now(), "image", 1L, 2L);
        productList.add(product);
        Page<Product> productPage = new PageImpl<>(productList, pageable, 1);

        when(productService.searchProducts("searchTerm", 1L, 2L, BigDecimal.valueOf(0), BigDecimal.valueOf(100), "location", pageable))
                .thenReturn(productPage);

        mockMvc.perform(get("/products/search")
                        .param("search", "searchTerm")
                        .param("category", "1")
                        .param("subcategory", "2")
                        .param("minPrice", "0")
                        .param("maxPrice", "100")
                        .param("location", "location")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("title"));
    }

    @Test
    public void testSearchProducts_withoutCategory() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);

        List<Product> productList = new ArrayList<>();
        Product product = new Product("title", BigDecimal.valueOf(20), "Warszawa", LocalDateTime.now(), "image", null, 2L);
        productList.add(product);
        Page<Product> productPage = new PageImpl<>(productList, pageable, 1);

        when(productService.searchProducts("searchTerm", null, 2L, BigDecimal.valueOf(0), BigDecimal.valueOf(100), "location", pageable))
                .thenReturn(productPage);

        mockMvc.perform(get("/products/search")
                        .param("search", "searchTerm")
                        .param("subcategory", "2")
                        .param("minPrice", "0")
                        .param("maxPrice", "100")
                        .param("location", "location")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("title"));
    }

}
