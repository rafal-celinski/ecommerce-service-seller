package pis24l.projekt.api_seller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import pis24l.projekt.api_seller.services.ImageAddService;
import pis24l.projekt.api_seller.services.ProductUpdateService;
import pis24l.projekt.api_seller.models.Image;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageAddController {

    private final ImageAddService imageAddService;
    private final ProductUpdateService productUpdateService;

    @Autowired
    public ImageAddController(ImageAddService imageAddService, ProductUpdateService productUpdateService) {
        this.imageAddService = imageAddService;
        this.productUpdateService = productUpdateService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFile(@RequestParam("image") MultipartFile file, @RequestParam("productId") String productId) {
        if (!imageAddService.isImageFile(file)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type.");
        }
        try {
            Image image = new Image(productId);
            String imageUrl = imageAddService.uploadImageToNginx(file, image.getId());

            productUpdateService.addImageUrlToProduct(productId, imageUrl);
            return ResponseEntity.status(HttpStatus.OK).body("Image uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
