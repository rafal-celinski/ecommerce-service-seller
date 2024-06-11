package pis24l.projekt.api_seller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis24l.projekt.api_seller.repositories.mongo.ProductRepository;
import pis24l.projekt.api_seller.model.Product;

import java.util.Optional;

@Service
public class ProductUpdateService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductUpdateService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void updateProductStatus(String productId, String status) throws Exception {
        Optional<Product> existingProduct = productRepository.findById(productId);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            if ("Bought".equals(product.getStatus())) {
                throw new Exception("Product with ID " + productId + " has already been bought.");
            }
            product.setStatus(status);
            productRepository.save(product);
        } else {
            System.out.println("Product with ID " + productId + " not found");
        }
    }
}
