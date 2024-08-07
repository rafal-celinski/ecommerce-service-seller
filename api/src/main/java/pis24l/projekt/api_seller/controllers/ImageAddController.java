package pis24l.projekt.api_seller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pis24l.projekt.api_seller.repositories.elastic.ProductAddRepository;
import pis24l.projekt.api_seller.services.ImageAddService;
import pis24l.projekt.api_seller.repositories.mongo.ProductRepository;
import pis24l.projekt.api_seller.models.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageAddController {

    private final ImageAddService imageAddService;
    private final ProductRepository productRepository;
    private final ProductAddRepository productAddRepository;

    @Autowired
    public ImageAddController(ImageAddService imageAddService, ProductRepository productRepository, ProductAddRepository productAddRepository) {
        this.imageAddService = imageAddService;
        this.productRepository = productRepository;
        this.productAddRepository = productAddRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFile(@RequestParam("image") MultipartFile file, @RequestParam("productId") String productId) {
        if (!imageAddService.isImageFile(file)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type.");
        }
        try {
            // Upload image to NGINX and get the URL
            String imageUrl = imageAddService.uploadImage(file, productId);

            // Find the product by ID
            Optional<Product> product = productRepository.findById(productId);
            Optional<Product> elasticProduct = productAddRepository.findById(productId);
            if (!product.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
            }

            Product newProduct = product.get();
            Product newElasticProduct = elasticProduct.get();
            ArrayList<String> ImageUrls = newProduct.getImageUrls();
            ImageUrls.add(imageUrl);
            newProduct.setImageUrls(ImageUrls);
            newElasticProduct.setImageUrls(ImageUrls);
            productRepository.save(newProduct);
            productAddRepository.save(newElasticProduct);

            return ResponseEntity.ok(product);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file: " + e.getMessage());
        } catch (Exception e) {
            // Catch any other exceptions that might not have been anticipated
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
