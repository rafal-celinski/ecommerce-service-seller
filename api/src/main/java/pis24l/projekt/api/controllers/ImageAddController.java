package pis24l.projekt.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pis24l.projekt.api.model.Image;
import pis24l.projekt.api.repositories.ImageRepository;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageAddController {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageAddController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addImage(
            @RequestParam("productId") Long productId,
            @RequestParam("name") String name,
            @RequestParam("image") MultipartFile imageFile) {

        try {
            byte[] bytes = imageFile.getBytes();
            Image image = new Image(productId, name, bytes);
            Image savedImage = imageRepository.save(image);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedImage);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image");
        }
    }
}