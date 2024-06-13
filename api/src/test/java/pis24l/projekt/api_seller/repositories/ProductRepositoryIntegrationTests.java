//package pis24l.projekt.api_seller.repositories;
//
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ProductRepositoryIntegrationTests {
//    @TestConfiguration
//    static class HelloControllerTestConfig {
//    }
//
////    Temporarily disabled for lack of testing database
////    @Autowired
////    private MockMvc mockMvc;
////
////    @Test
////    public void testSearchProducts() throws Exception {
////        mockMvc.perform(get("/products/search")
////                        .param("minPrice", "0.0")
////                        .param("maxPrice", "20000.0"))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.length()").value(54));
////    }
//}