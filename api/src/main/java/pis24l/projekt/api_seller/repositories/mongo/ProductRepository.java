package pis24l.projekt.api_seller.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api_seller.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
