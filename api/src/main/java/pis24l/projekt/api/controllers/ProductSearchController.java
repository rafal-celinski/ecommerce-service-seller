package pis24l.projekt.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pis24l.projekt.api.model.Product;
import pis24l.projekt.api.repositories.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductSearchController {
    private final ProductRepository productRepository;

    public ProductSearchController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam(required = false) BigDecimal minPrice,
                                        @RequestParam(required = false) BigDecimal maxPrice,
                                        @RequestParam(required = false) LocalDate date,
                                        @RequestParam(required = false) String sellerName,
                                        @RequestParam(required = false) String name) {
        return productRepository.findByCriteria(minPrice, maxPrice, date, sellerName, name);
    }
}
