package pis24l.projekt.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis24l.projekt.api.model.Image;
import pis24l.projekt.api.repositories.ImageRepository;

import java.util.List;

@Service
public class ImageSearchService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageSearchService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> getAllImagesForProduct(Long productId) {
        return imageRepository.findByProductId(productId);
    }

    public Image getFirstImageForProduct(Long productId) {
        List<Image> images = imageRepository.findByProductId(productId);
        return images.isEmpty() ? null : images.get(0);
    }
}
