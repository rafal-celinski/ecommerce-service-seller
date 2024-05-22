package pis24l.projekt.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/images")
public class ImageUploadController {

    private static final String DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            saveFile(file);
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Could not upload the file: " + file.getOriginalFilename());
        }
    }

    private void saveFile(MultipartFile file) throws IOException {
        if (!Files.exists(Paths.get(DIRECTORY))) {
            Files.createDirectories(Paths.get(DIRECTORY));
        }
        Path filePath = Paths.get(DIRECTORY, file.getOriginalFilename());
        file.transferTo(filePath);
    }
}

