package pis24l.projekt.api_seller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pis24l.projekt.api_seller.model.Image;
import pis24l.projekt.api_seller.model.Product;
import pis24l.projekt.api_seller.repositories.ImageRepository;
import pis24l.projekt.api_seller.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductSearchService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ProductSearchService(ProductRepository productRepository, ImageRepository imageRepository, MongoTemplate mongoTemplate) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Page<Product> searchProducts(String search, String category, String subcategory, BigDecimal minPrice, BigDecimal maxPrice, String location, Pageable pageable) {
        Query query = new Query();

        if (search != null && !search.isEmpty()) {
            query.addCriteria(Criteria.where("title").regex(search, "i"));
        }
        if (category != null && !category.isEmpty()) {
            query.addCriteria(Criteria.where("category").is(category));
        }
        if (subcategory != null && !subcategory.isEmpty()) {
            query.addCriteria(Criteria.where("subcategory").is(subcategory));
        }
        if (minPrice != null) {
            query.addCriteria(Criteria.where("price").gte(minPrice));
        }
        if (maxPrice != null) {
            query.addCriteria(Criteria.where("price").lte(maxPrice));
        }
        if (location != null && !location.isEmpty()) {
            query.addCriteria(Criteria.where("location").regex(location, "i"));
        }

        long total = mongoTemplate.count(query, Product.class);

        query.with(pageable);
        List<Product> resultList = mongoTemplate.find(query, Product.class);

        // Fetch and set image URLs
        for (Product product : resultList) {
            List<Image> images = imageRepository.findByProductId(product.getId());
            if (!images.isEmpty()) {
                Image firstImage = images.get(0); // Get the first image
                String imageUrl = "/images/" + firstImage.getId(); // Map the URL
                product.setImageUrls(List.of(imageUrl)); // Set the list with the single URL
            }
        }

        return new PageImpl<>(resultList, pageable, total);
    }

    public Product getProductById(String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            List<Image> images = imageRepository.findByProductId(productId);
            List<String> imageUrls = images.stream()
                    .map(image -> "/images/" + image.getId())
                    .collect(Collectors.toList());
            product.setImageUrls(imageUrls);
            return product;
        } else {
            throw new RuntimeException("Product not found with id " + productId);
        }
    }
}
