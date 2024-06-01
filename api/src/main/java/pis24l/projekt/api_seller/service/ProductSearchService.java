package pis24l.projekt.api_seller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pis24l.projekt.api_seller.model.Image;
import pis24l.projekt.api_seller.model.Product;
import pis24l.projekt.api_seller.repositories.ImageRepository;
import pis24l.projekt.api_seller.repositories.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductSearchService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private EntityManager entityManager;

    @Autowired
    public ProductSearchService(ProductRepository productRepository, EntityManager entityManager, ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
        this.entityManager = entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Page<Product> searchProducts(String search, Long category, Long subcategory, BigDecimal minPrice, BigDecimal maxPrice, String location, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (search != null && !search.isEmpty()) {
            predicates.add(cb.like(root.get("title"), "%" + search + "%"));
        }
        if (category != null) {
            predicates.add(cb.equal(root.get("category"), category));
        }
        if (subcategory != null) {
            predicates.add(cb.equal(root.get("subcategory"), subcategory));
        }
        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }
        if (location != null && !location.isEmpty()) {
            predicates.add(cb.like(root.get("location"), "%" + location + "%"));
        }

        query.where(predicates.toArray(new Predicate[0]));

        // Add pagination
        List<Product> resultList = entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        // Fetch and set image URLs
        for (Product product : resultList) {
            List<Image> images = imageRepository.findByProductId(product.getId());
            if (!images.isEmpty()) {
                Image firstImage = images.get(0); // Get the first image
                String imageUrl = "/images/" + firstImage.getId(); // Map the URL
                product.setImageUrls(List.of(imageUrl)); // Set the list with the single URL
            }
        }

        // Count total records for pagination metadata
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Product> countRoot = countQuery.from(Product.class);
        countQuery.select(cb.count(countRoot)).where(predicates.toArray(new Predicate[0]));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return new org.springframework.data.domain.PageImpl<>(resultList, pageable, count);
    }

    @Transactional
    public Product getProductById(Long productId) {
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
