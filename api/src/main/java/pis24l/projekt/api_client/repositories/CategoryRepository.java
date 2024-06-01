package pis24l.projekt.api_client.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api_client.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
