package pis24l.projekt.api_seller.repositories.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api_seller.model.Product;

@Repository
public interface ProductAddRepository extends ElasticsearchRepository<Product, Long> {
}
