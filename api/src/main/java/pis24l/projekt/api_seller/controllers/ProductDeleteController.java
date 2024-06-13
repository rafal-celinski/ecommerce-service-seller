package pis24l.projekt.api_seller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pis24l.projekt.api_seller.repositories.mongo.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductDeleteController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDeleteController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }
}
