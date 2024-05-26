package pis24l.projekt.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pis24l.projekt.api.repositories.ProductRepository;
import pis24l.projekt.api.model.Product;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
@RequestMapping("/products/add")
public class ProductAddController {

    private final ProductRepository productRepository;
    @Autowired
    public ProductAddController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }
}
