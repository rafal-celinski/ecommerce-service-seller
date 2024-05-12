package pis24l.projekt.api.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductRepositoryIntegrationTests {
    @TestConfiguration
    @ComponentScan(basePackages = "pis24l.projekt.api")
    static class HelloControllerTestConfig {
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSearchProducts() throws Exception {
        mockMvc.perform(get("/products/search")
                        .param("minPrice", "10.0")
                        .param("maxPrice", "20.0")
                        .param("date", "2024-05-13"))// Example date in yyyy-MM-dd format
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Product A"))
                .andExpect(jsonPath("$[1].name").value("Product B"));

    }
}