package pis24l.projekt.api_seller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pis24l.projekt.api_seller.models.Product;
import pis24l.projekt.api_seller.models.ProductStatus;
import pis24l.projekt.api_seller.repositories.elastic.ProductAddRepository;
import pis24l.projekt.api_seller.repositories.mongo.ProductRepository;

import java.util.Optional;

@Service
public class ProductDeleteService {

    private final ProductRepository productRepository;
    private final ProductAddRepository productAddRepository;

    @Autowired
    public ProductDeleteService(ProductRepository productRepository, ProductAddRepository productAddRepository) {
        this.productRepository = productRepository;
        this.productAddRepository = productAddRepository;
    }

    public ResponseEntity<?> deleteProduct(String id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        Product product = productOptional.get();
        if (product.getStatus() == ProductStatus.SOLD) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Product cannot be deleted as it is SOLD");
        } else if (product.getStatus() == ProductStatus.UP) {
            productRepository.deleteById(id);
            productAddRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else if (product.getStatus() == ProductStatus.SENT) {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    public ResponseEntity<?> deleteProductFromElastic(String id) {
        Optional<Product> productOptional = productAddRepository.findById(id);
        if (!productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        productAddRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
