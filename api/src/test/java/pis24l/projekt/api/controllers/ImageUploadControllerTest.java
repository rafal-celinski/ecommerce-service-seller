package pis24l.projekt.api.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ImageUploadController.class)
public class ImageUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldUploadFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "testimage.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "Test image content".getBytes()
        );

        mockMvc.perform(multipart("/images/upload").file(file))
                .andExpect(status().isOk());
    }
}

