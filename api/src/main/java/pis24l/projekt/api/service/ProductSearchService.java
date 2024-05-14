package pis24l.projekt.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis24l.projekt.api.model.Product;
import pis24l.projekt.api.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductSearchService {

    private final ProductRepository productRepository;

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
            return productRepository.findByPriceBetweenAndTitleContainingAndCategoryAndSubcategoryAndLocation(
                    minPrice, maxPrice, search, category, subcategory, location);
        }
    }


}
