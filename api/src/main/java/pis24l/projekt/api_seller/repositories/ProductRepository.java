package pis24l.projekt.api_seller.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pis24l.projekt.api_seller.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
