package pis24l.projekt.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pis24l.projekt.api.model.Product;
import pis24l.projekt.api.repositories.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSearchService {

    private final ProductRepository productRepository;
    private EntityManager entityManager;

    @Autowired
    public ProductSearchService(ProductRepository productRepository, EntityManager entityManager) {
        this.productRepository = productRepository;
        this.entityManager = entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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

        // Count total records for pagination metadata
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Product> countRoot = countQuery.from(Product.class);
        countQuery.select(cb.count(countRoot)).where(predicates.toArray(new Predicate[0]));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return new org.springframework.data.domain.PageImpl<>(resultList, pageable, count);
    }
}
