package pis24l.projekt.api_seller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pis24l.projekt.api_seller.kafka.controllers.OrderController;
import pis24l.projekt.api_seller.models.Product;
import pis24l.projekt.api_seller.models.ProductStatus;
import pis24l.projekt.api_seller.repositories.mongo.ProductRepository;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
@RequestMapping("/products")
public class ProductUpdateController {

    private final ProductRepository productRepository;
    private final OrderController orderController;


    @Autowired
    public ProductUpdateController(ProductRepository productRepository, OrderController orderController) {
        this.productRepository = productRepository;
        this.orderController = orderController;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody @Valid Product product, BindingResult bindingResult) {
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
        updatedProduct.setCategory(product.getCategory());
        updatedProduct.setSubcategory(product.getSubcategory());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setStatus(product.getStatus());

        productRepository.save(updatedProduct);

        return ResponseEntity.ok(updatedProduct);
    }

    @PutMapping("/send/{id}")
    public ResponseEntity<?> updateStatusToSent(@PathVariable String id) {

        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        Product product = productOptional.get();
        if (product.getStatus() == ProductStatus.SOLD) {
            product.setStatus(ProductStatus.SENT);
            productRepository.save(product);

            return ResponseEntity.status(HttpStatus.OK).body("Product status updated to SENT");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product status is not SOLD, cannot update to SENT");
        }
    }
}