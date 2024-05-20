package pis24l.projekt.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis24l.projekt.api.model.Product;
import pis24l.projekt.api.repositories.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSearchService {

    private final ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ProductSearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> searchProducts(String search,
                                        Long category,
                                        Long subcategory,
                                        BigDecimal minPrice,
                                        BigDecimal maxPrice,
                                        String location,
                                        Boolean isTesting) {
        if (!isTesting) {
            return productRepository.findAll();
        } else {
            return searchProductsWithCriteria(search, category, subcategory, minPrice, maxPrice, location);
        }
    }

    private List<Product> searchProductsWithCriteria(String search,
                                                     Long category,
                                                     Long subcategory,
                                                     BigDecimal minPrice,
                                                     BigDecimal maxPrice,
                                                     String location) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (search != null && !search.isEmpty()) {
            predicates.add(cb.like(product.get("title"), "%" + search + "%"));
        }
        if (category != null) {
            predicates.add(cb.equal(product.get("category"), category));
        }
        if (subcategory != null) {
            predicates.add(cb.equal(product.get("subcategory"), subcategory));
        }
        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("price"), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(product.get("price"), maxPrice));
        }
        if (location != null && !location.isEmpty()) {
            predicates.add(cb.equal(product.get("location"), location));
        }

        query.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}
