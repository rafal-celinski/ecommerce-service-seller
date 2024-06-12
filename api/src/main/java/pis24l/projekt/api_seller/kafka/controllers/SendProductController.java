package pis24l.projekt.api_seller.kafka.controllers;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pis24l.projekt.api_seller.models.Product;
import pis24l.projekt.api_seller.models.ProductStatus;
import pis24l.projekt.api_seller.repositories.mongo.ProductRepository;

import java.util.Optional;


@RestController
@RequestMapping("/send/{id}")
public class SendProductController {

    private final ProductRepository productRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    SendProductController(ProductRepository productRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.productRepository = productRepository;
        this.kafkaTemplate = kafkaTemplate;
    }
    @PutMapping
    public ResponseEntity<?> updateStatusToSent(@PathVariable String id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        Product product = productOptional.get();
        if (product.getStatus() == ProductStatus.SOLD) {
            product.setStatus(ProductStatus.SENT);
            productRepository.save(product);
            kafkaTemplate.send("product-send", id);
            return ResponseEntity.status(HttpStatus.OK).body("Product status updated to SENT");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product status is not SOLD, cannot update to SENT");
        }
}
