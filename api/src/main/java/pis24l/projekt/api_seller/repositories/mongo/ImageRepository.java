package pis24l.projekt.api_seller.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api_seller.models.Image;

@Repository
public interface ImageRepository extends MongoRepository <Image, String> {
}
