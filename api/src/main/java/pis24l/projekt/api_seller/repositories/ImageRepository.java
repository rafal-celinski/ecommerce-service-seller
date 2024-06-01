package pis24l.projekt.api_seller.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pis24l.projekt.api_seller.model.Image;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProductId(Long productId);

}
