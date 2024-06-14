package pis24l.projekt.api_seller.repositories.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api_seller.models.Product;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductAddRepository extends ElasticsearchRepository<Product, String> {
    Optional<Product> findById(String id);
}
