package pis24l.projekt.api_seller.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ImageAddService {

    @Value("${nginx.upload.url}")
    private String nginxUploadUrl;

    @Value("${nginx.url}")
    private String nginxUrl;

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

    public String uploadImageToNginx(MultipartFile file, String id) throws IOException {
        String fileName = file.getOriginalFilename();
        String fileExtension = FilenameUtils.getExtension(fileName);
        String saveFileName = id + "." + fileExtension;
        URL url = new URL(nginxUploadUrl + "/" + saveFileName);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", file.getContentType());
        connection.getOutputStream().write(file.getBytes());
        connection.getOutputStream().close();

        int responseCode = connection.getResponseCode();
        if (responseCode == 200 || responseCode == 201) {
            return nginxUrl + "/images/" + saveFileName;
        } else {
            throw new IOException("Failed to upload the file to Nginx. Response code: " + responseCode);
        }
    }
}
