package pis24l.projekt.api_seller.repositories.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api_seller.models.Product;
import pis24l.projekt.api_seller.models.ProductStatus;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Page<Product> findByStatus(ProductStatus status, Pageable pageable);
}
