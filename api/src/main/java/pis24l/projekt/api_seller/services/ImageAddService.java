package pis24l.projekt.api_seller.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import pis24l.projekt.api_seller.models.Image;
import pis24l.projekt.api_seller.repositories.mongo.ImageRepository;

import java.io.IOException;

@Service
public class ImageAddService {

    private final ImageRepository imageRepository;

    @Value("${nginx.server.url}")
    private String nginxServerUrl;

    @Autowired
    public ImageAddService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String uploadImage(MultipartFile file, String productId) throws IOException {
        // Create and save the Image object
        Image image = new Image(productId);
        Image savedImage = imageRepository.save(image);
        String imageId = savedImage.getId();

        // Create the file name based on the image ID
        String fileName = file.getOriginalFilename();
        String fileExtension = FilenameUtils.getExtension(fileName);
        String saveFileName = imageId + "." + fileExtension;

        // Upload the file to NGINX
        String uploadUrl = nginxServerUrl + "/upload/" + saveFileName;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(uploadUrl, file.getBytes());

        return uploadUrl;
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
