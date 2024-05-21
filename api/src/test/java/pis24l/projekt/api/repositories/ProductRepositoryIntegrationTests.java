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
    static class HelloControllerTestConfig {
    }

//    Temporarily disabled for lack of testing database
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testSearchProducts() throws Exception {
//        mockMvc.perform(get("/products/search")
//                        .param("minPrice", "0.0")
//                        .param("maxPrice", "20000.0"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(54));
//    }
}