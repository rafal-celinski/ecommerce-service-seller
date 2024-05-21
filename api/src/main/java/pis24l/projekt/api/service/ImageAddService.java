package pis24l.projekt.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis24l.projekt.api.model.Image;
import pis24l.projekt.api.repositories.ImageRepository;

@Service
public class ImageAddService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageAddService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image add(byte[] imageBytes, String name, Long productId) {
        Image image = new Image(productId, name, imageBytes);
        return imageRepository.save(image);
    }
}