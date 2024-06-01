package pis24l.projekt.api_seller.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api_seller.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
