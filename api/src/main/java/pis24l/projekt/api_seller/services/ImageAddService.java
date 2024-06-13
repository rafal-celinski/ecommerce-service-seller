package pis24l.projekt.api_seller.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageAddService {

    @Value("${nginx.server.url}")
    private String nginxServerUrl;

    public String uploadImage(MultipartFile file, String id) throws IOException {
        String fileName = file.getOriginalFilename();
        String fileExtension = FilenameUtils.getExtension(fileName);
        String saveFileName = id + "." + fileExtension;

        String uploadUrl = nginxServerUrl + "/upload/" + saveFileName;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(uploadUrl, file.getBytes());

        return nginxServerUrl + "/upload/" + saveFileName;
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
