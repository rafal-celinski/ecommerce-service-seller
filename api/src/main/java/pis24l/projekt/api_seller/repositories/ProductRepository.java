package pis24l.projekt.api_seller.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api_seller.model.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
