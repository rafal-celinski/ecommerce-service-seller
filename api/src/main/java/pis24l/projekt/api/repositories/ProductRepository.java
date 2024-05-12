package pis24l.projekt.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api.model.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "AND (:date IS NULL OR p.date = :date) " +
            "AND (:sellerName IS NULL OR p.sellerName = :sellerName) " +
            "AND (:name IS NULL OR LOWER(p.name) LIKE LOWER(concat('%', :name, '%')))")
    List<Product> findByCriteria(@Param("minPrice") BigDecimal minPrice,
                                 @Param("maxPrice") BigDecimal maxPrice,
                                 @Param("date") LocalDate date,
                                 @Param("sellerName") String sellerName,
                                 @Param("name") String name);
}
