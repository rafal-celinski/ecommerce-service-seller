package pis24l.projekt.api_seller.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api_seller.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
