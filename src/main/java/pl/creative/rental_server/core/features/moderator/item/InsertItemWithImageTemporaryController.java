package pl.creative.rental_server.core.features.moderator.item;

import lombok.RequiredArgsConstructor;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.creative.rental_server.core.global.handlersAndUtils.FileUploader;
import pl.creative.rental_server.core.global.handlersAndUtils.ImageService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Deprecated(forRemoval = true)
public class InsertItemWithImageTemporaryController {
    private final FileUploader fileUploader;
    private final ImageService imageService;

    @Deprecated(forRemoval = true)
    @PostMapping("/upload/image")
    public ResponseEntity<?> decodeMultipartFile(@RequestBody MultipartFile multipartFile, @RequestParam String fileName)
            throws IOException {


        multipartFile.transferTo(Path.of("temp.png"));
        File transferred = new File("temp.png");

        FileWriter fileWriter = new FileWriter(fileName + ".txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        byte[] fileBytes = Base64.encode(Files.readAllBytes(transferred.toPath()));
        bufferedWriter.write(new String(fileBytes));


        return ResponseEntity.ok().build();
    }

    @Deprecated(forRemoval = true)
    @PostMapping("/upload/imageFromString")
    public ResponseEntity<?> insertItemWithImageFromString(@RequestBody String base64EncodedFile) {

        fileUploader.uploadFile(base64EncodedFile);
        return ResponseEntity.ok().build();
    }

    @Deprecated(forRemoval = true)
    @PostMapping("/upload/imageFromStringList")
    public ResponseEntity<?> insertItemWithImageFromStringList(@RequestBody List<String> base64EncodedFile) {
        imageService.addImages("0", base64EncodedFile);
        return ResponseEntity.ok().build();
    }
}
