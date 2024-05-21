package pis24l.projekt.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
