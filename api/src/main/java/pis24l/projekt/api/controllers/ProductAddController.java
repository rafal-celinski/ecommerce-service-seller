package pis24l.projekt.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pis24l.projekt.api.repositories.ProductRepository;
import pis24l.projekt.api.model.Product;

@RestController
@RequestMapping("/products/add")
public class ProductAddController {

    @Autowired
    private ProductRepository productRepository;
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody @Validated Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //Add error handling
            return ResponseEntity.badRequest().build();
        }
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }
}
