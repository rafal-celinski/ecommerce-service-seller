package pis24l.projekt.api_seller.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface FileStorage {
    void storeFile(Path directoryPath, MultipartFile file, String saveFileName) throws IOException;
}
