package pis24l.projekt.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pis24l.projekt.api.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
