package pis24l.projekt.api_client;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
class ApiApplicationTests {

    @TestConfiguration
    @ComponentScan(basePackages = "pis24l.projekt.api_client")
    static class HelloControllerTestConfig {
        // This class will be automatically picked up by Spring Boot as a test configuration
    }
    @Test
    void contextLoads() {
    }

}
