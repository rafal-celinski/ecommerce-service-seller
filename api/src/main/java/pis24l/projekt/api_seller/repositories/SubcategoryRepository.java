package pis24l.projekt.api_seller.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pis24l.projekt.api_seller.model.Subcategory;
import java.util.List;

public interface SubcategoryRepository extends MongoRepository<Subcategory, String> {
    List<Subcategory> findByCategoryId(String categoryId);
}
