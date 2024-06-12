package pis24l.projekt.api_seller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pis24l.projekt.api_seller.models.Product;
import pis24l.projekt.api_seller.services.ProductSearchService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
@RequestMapping("/products")
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    @Autowired
    public ProductSearchController(ProductSearchService productSearchService) {
        this.productSearchService = productSearchService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productSearchService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> searchProducts(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String subcategory,
            @RequestParam(required = false) String location,
            Pageable pageable) {
        Page<Product> products = productSearchService.searchProducts(search, category, subcategory, location, pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Product>> listAllProducts(Pageable pageable) {
        Page<Product> products = productSearchService.listAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/in-progress")
    public ResponseEntity<Page<Product>> listProductsInProgress(Pageable pageable) {
        Page<Product> products = productSearchService.listProductsInProgress(pageable);
        return ResponseEntity.ok(products);
    }
    
}
