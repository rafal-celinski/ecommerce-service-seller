package pis24l.projekt.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis24l.projekt.api.model.Image;
import pis24l.projekt.api.repositories.ImageRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ImageSearchService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageSearchService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Transactional
    public Optional<Image> getImageById(Long imageId) {
        return imageRepository.findById(imageId);
    }
}
