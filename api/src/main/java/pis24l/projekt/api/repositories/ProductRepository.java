package pis24l.projekt.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api.model.Product;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByPriceBetweenAndTitleContainingAndCategoryAndSubcategoryAndLocation(
            BigDecimal minPrice, BigDecimal maxPrice, String search, Long category, Long subcategory, String Location);

}
