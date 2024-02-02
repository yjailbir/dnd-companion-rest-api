package program.service.dnd.contorller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/image")
public class ImagesController {
    @GetMapping("/{imgName}")
    public ResponseEntity<byte[]> getImage(@PathVariable("imgName") String imgName) throws IOException {
        String imageDirectory = "src/main/resources/static/images/";
        File imgFile = new File(imageDirectory + imgName);

        byte[] bytes = Files.readAllBytes(imgFile.toPath());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
