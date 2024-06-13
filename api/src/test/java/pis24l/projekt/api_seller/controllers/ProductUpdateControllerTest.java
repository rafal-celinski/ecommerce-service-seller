package pis24l.projekt.api_seller.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pis24l.projekt.api_seller.models.Product;
import pis24l.projekt.api_seller.models.ProductStatus;
import pis24l.projekt.api_seller.repositories.mongo.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductUpdateController.class)
public class ProductUpdateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Configuration
    static class TestConfig {
        @Bean
        public MongoTemplate mongoTemplate() {
            return Mockito.mock(MongoTemplate.class);
        }

        @Bean
        public ProductUpdateController productUpdateController(ProductRepository productRepository) {
            return new ProductUpdateController(productRepository);
        }
    }

    @Test
    public void whenUpdateProductWithExistingId_thenProductIsUpdated() throws Exception {
        Product product = new Product("Laptop", BigDecimal.valueOf(1200.00), "Online", "Electronics", "Laptops", "High performance laptop", ProductStatus.UP);
        product.setId("XDXD");
        given(productRepository.findById("XDXD")).willReturn(Optional.of(product));
        given(productRepository.save(any(Product.class))).willReturn(product);

        mockMvc.perform(put("/products/update/{id}", "XDXD")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Laptop\"," +
                                "\"price\":1300.00," +
                                "\"location\":\"Store\"," +
                                "\"category\":\"Electronics\"," +
                                "\"subcategory\":\"Laptops\"," +
                                "\"description\":\"Updated description\"," +
                                "\"status\":\"UP\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Laptop"))
                .andExpect(jsonPath("$.description").value("Updated description"));

        verify(productRepository).save(any(Product.class));
    }

    @Test
    public void whenUpdateProductWithNonExistingId_thenNotFound() throws Exception {
        given(productRepository.findById("XDXD")).willReturn(Optional.empty());

        mockMvc.perform(put("/products/update/{id}", "XD")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Laptop\"," +
                                "\"price\":1300.00," +
                                "\"location\":\"Store\"," +
                                "\"category\":\"Electronics\"," +
                                "\"subcategory\":\"New_subcategory\"," +
                                "\"description\":\"Updated description\"}"))
                .andExpect(status().isNotFound());

        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    public void whenUpdateProductWithExistingIdButIncorrectData_thenProductIsNotUpdated() throws Exception {
        Product product = new Product("Laptop", BigDecimal.valueOf(1200.00), "Online", "Electronics", "Laptops", "High performance laptop", ProductStatus.UP);
        product.setId("XDXD");
        given(productRepository.findById("XDXD")).willReturn(Optional.of(product));

        mockMvc.perform(put("/products/update/{id}", "XDXD")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Laptop\"," +
                                "\"price\":-1300.00," +
                                "\"location\":\"Store\"," +
                                "\"category\":\"Electronics\"," +
                                "\"subcategory\":\"New_subcategory\"," +
                                "\"description\":\"Updated description\"}"))
                .andExpect(status().isBadRequest());

        verify(productRepository, never()).save(any(Product.class));
    }
}
