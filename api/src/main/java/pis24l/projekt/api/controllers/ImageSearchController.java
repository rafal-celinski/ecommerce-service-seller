package pis24l.projekt.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pis24l.projekt.api.model.Image;
import pis24l.projekt.api.service.ImageSearchService;

import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageSearchController {

    private final ImageSearchService imageSearchService;

    @Autowired
    public ImageSearchController(ImageSearchService imageSearchService) {
        this.imageSearchService = imageSearchService;
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long imageId) {
        Optional<Image> imageOptional = imageSearchService.getImageById(imageId);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(image.getImage());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
