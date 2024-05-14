package pis24l.projekt.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pis24l.projekt.api.model.Product;
import pis24l.projekt.api.service.ProductSearchService;


import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductSearchController {

    private final ProductSearchService productService;

    @Autowired
    public ProductSearchController(ProductSearchService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) Long subcategory,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String location,
            @RequestParam(required = false, defaultValue = "false") Boolean isTesting) {
        return productService.searchProducts(search, category, subcategory, minPrice, maxPrice, location, isTesting);
    }
}
