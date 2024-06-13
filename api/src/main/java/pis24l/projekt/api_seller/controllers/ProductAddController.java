package pis24l.projekt.api_seller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pis24l.projekt.api_seller.models.Product;
import pis24l.projekt.api_seller.repositories.mongo.ProductRepository;
import pis24l.projekt.api_seller.repositories.elastic.ProductAddRepository;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/products")
public class ProductAddController {

    private final ProductRepository productRepository;
    private final ProductAddRepository productAddRepository;

    @Autowired
    public ProductAddController(ProductRepository productRepository, ProductAddRepository productAddRepository) {
        this.productRepository = productRepository;
        this.productAddRepository = productAddRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        Product savedProduct = productAddRepository.save(product);
        savedProduct.setDate(LocalDateTime.now());
        productRepository.save(savedProduct);

        return ResponseEntity.ok(savedProduct);
    }
}
