package pis24l.projekt.api_seller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis24l.projekt.api_seller.models.ProductStatus;
import pis24l.projekt.api_seller.repositories.mongo.ProductRepository;
import pis24l.projekt.api_seller.models.Product;

import java.util.Optional;

@Service
public class ProductUpdateService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductUpdateService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void updateProductStatus(String productId, ProductStatus status) throws Exception {
        Optional<Product> existingProduct = productRepository.findById(productId);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            if (ProductStatus.SOLD.equals(product.getStatus())) {
                throw new Exception("Product with ID " + productId + " has already been bought.");
            }
            product.setStatus(status);
            productRepository.save(product);
        } else {
            throw new Exception("Product with ID " + productId + " not found");
        }
    }

    public void addImageUrlToProduct(String productId, String imageUrl) throws Exception {
        Optional<Product> existingProduct = productRepository.findById(productId);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.getImageUrls().add(imageUrl);
            productRepository.save(product);
        } else {
            throw new Exception("Product with ID " + productId + " not found");
        }
    }
}