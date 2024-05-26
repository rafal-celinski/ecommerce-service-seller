package pis24l.projekt.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
