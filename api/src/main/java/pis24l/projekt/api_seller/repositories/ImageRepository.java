package pis24l.projekt.api_seller.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pis24l.projekt.api_seller.model.Image;

import java.util.List;

public interface ImageRepository extends MongoRepository<Image, String> {
    List<Image> findByProductId(String productId);
}
