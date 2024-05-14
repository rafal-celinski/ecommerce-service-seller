package pis24l.projekt.api.controllers;

import org.apache.tomcat.jni.Local;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pis24l.projekt.api.model.Product;
import pis24l.projekt.api.repositories.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductSearchController {
    private final ProductRepository productRepository;

    public ProductSearchController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) Long subcategory,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String location,
            @RequestParam(required = false,defaultValue = "false") Boolean isTesting) {
        // Assuming search is for the product title
        if (!isTesting){
            return productRepository.findAll();
        }
        else {
            return productRepository.findByPriceBetweenAndTitleContainingAndCategoryAndSubcategoryAndLocation(
                    minPrice, maxPrice, search, category, subcategory, location);
        }
    }
}
