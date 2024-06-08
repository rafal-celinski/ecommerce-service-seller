package pis24l.projekt.api_seller.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pis24l.projekt.api_seller.model.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
}

