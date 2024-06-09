package pis24l.projekt.api_seller.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api_seller.model.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Long> {

}
