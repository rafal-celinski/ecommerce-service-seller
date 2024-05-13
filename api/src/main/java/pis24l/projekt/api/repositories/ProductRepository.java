package pis24l.projekt.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api.model.Product;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM Product p WHERE (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "AND (coalesce(:date, null) is null OR p.date_added = :date) " +
            "AND (:sellerName IS NULL OR p.seller_name = cast(:sellerName as text)) " +
            "AND (:name IS NULL OR p.name LIKE cast(:name as text))", nativeQuery = true)
    List<Product> findByCriteria(@Param("minPrice") BigDecimal minPrice,
                                 @Param("maxPrice") BigDecimal maxPrice,
                                 @Param("date") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date date,
                                 @Param("sellerName") String sellerName,
                                 @Param("name") String name);
}
