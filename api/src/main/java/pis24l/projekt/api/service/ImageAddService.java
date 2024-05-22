package pis24l.projekt.api.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

@Service
public class ImageAddService {
    private static final String DIRECTORY = System.getProperty("user.dir") + "/uploads";

    public void uploadImage(MultipartFile file, Long id) throws IOException {
        Path directoryPath = Paths.get(DIRECTORY);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
        String fileName = file.getOriginalFilename();
        String fileExtension = FilenameUtils.getExtension(fileName);
        String saveFileName = id + "." + fileExtension;
        Path filePath = directoryPath.resolve(saveFileName);
        file.transferTo(filePath);
        }

    public boolean isImageFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName == null) return false;

        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return fileExtension.equals("jpg") ||
                fileExtension.equals("jpeg") ||
                fileExtension.equals("png") ||
                fileExtension.equals("gif") ||
                fileExtension.equals("bmp");
    }
}