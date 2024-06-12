package pis24l.projekt.api_seller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import pis24l.projekt.api_seller.models.Product;
import pis24l.projekt.api_seller.repositories.elastic.ProductAddRepository;
import pis24l.projekt.api_seller.repositories.mongo.ProductRepository;
import pis24l.projekt.api_seller.models.ProductStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSearchService {

    private final ProductRepository productRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ProductSearchService(ProductRepository productRepository, MongoTemplate mongoTemplate, ProductAddRepository productAddRepository) {
        this.productRepository = productRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Page<Product> searchProducts(String search, String category, String subcategory, String location, Pageable pageable) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();

        if (search != null && !search.isEmpty()) {
            criteriaList.add(Criteria.where("title").regex(search, "i"));
        }
        if (category != null && !category.isEmpty()) {
            criteriaList.add(Criteria.where("category").is(category));
        }
        if (subcategory != null && !subcategory.isEmpty()) {
            criteriaList.add(Criteria.where("subcategory").is(subcategory));
        }

        if (location != null && !location.isEmpty()) {
            criteriaList.add(Criteria.where("location").regex(location, "i"));
        }

        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        query.with(pageable);

        List<Product> products = mongoTemplate.find(query, Product.class);
        long count = mongoTemplate.count(query, Product.class);

        return new PageImpl<>(products, pageable, count);
    }

    public Product getProductById(String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.orElseThrow(() -> new RuntimeException("Product not found with id " + productId));
    }

    public Page<Product> listAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> listProductsInProgress(Pageable pageable) {
        return productRepository.findByStatus(ProductStatus.SOLD, pageable);
    }
}
