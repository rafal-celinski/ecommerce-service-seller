package pis24l.projekt.api_seller.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pis24l.projekt.api_seller.models.Product;
import pis24l.projekt.api_seller.models.ProductStatus;
import pis24l.projekt.api_seller.repositories.mongo.ProductRepository;
import pis24l.projekt.api_seller.repositories.elastic.ProductAddRepository;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class ProductAddControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductAddRepository productAddRepository;

    @InjectMocks
    private ProductAddController productAddController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productAddController).build();
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void whenPostRequestToProductsAndValidProduct_thenCorrectResponse() throws Exception {
        Product product = new Product("Laptop", BigDecimal.valueOf(999.99), "Warsaw", "Electronics", "Laptops", "High performance laptop with latest specifications", ProductStatus.UP);
        given(productAddRepository.save(any(Product.class))).willReturn(product);

        mockMvc.perform(post("/products/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Laptop\"," +
                                "\"price\":999.99," +
                                "\"location\": \"Warsaw\"," +
                                "\"category\": \"Electronics\"," +
                                "\"subcategory\": \"Laptops\"," +
                                "\"description\": \"High performance laptop with latest specifications\"," +
                                "\"status\": \"UP\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Laptop"));
    }

    @Test
    public void whenPostRequestToProductsAndInvalidProduct_thenBadRequest() throws Exception {
        mockMvc.perform(post("/products/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Laptop\"," +
                                "\"price\":999.99," +
                                "\"location\": \"Warsaw\"," +
                                "\"category\": \"Electronics\"," +
                                "\"description\": \"High performance laptop with latest specifications\"}"))
                .andExpect(status().isBadRequest());
    }
}
