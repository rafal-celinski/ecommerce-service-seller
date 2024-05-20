package pis24l.projekt.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pis24l.projekt.api.model.Product;
import pis24l.projekt.api.repositories.ProductRepository;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductUpdateController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductUpdateController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        Optional<Product> existingProduct = productRepository.findById(id);
        if (!existingProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        Product updatedProduct = existingProduct.get();
        updatedProduct.setTitle(product.getTitle());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setLocation(product.getLocation());
        updatedProduct.setDateAdded(product.getDateAdded());
        updatedProduct.setImageUrl(product.getImageUrl());
        updatedProduct.setCategory(product.getCategory());
        updatedProduct.setSubcategory(product.getSubcategory());

        productRepository.save(updatedProduct);

        return ResponseEntity.ok(updatedProduct);
    }
}