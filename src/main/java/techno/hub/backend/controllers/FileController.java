package techno.hub.backend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import techno.hub.backend.dtos.BlogCreateRequestDto;
import techno.hub.backend.dtos.BlogDto;
import techno.hub.backend.services.StorageService;

import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/files")
@AllArgsConstructor
public class FileController {

    private final StorageService storageService;

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        try {
            String contentType = Files.probeContentType(file.getFile().toPath());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
        } catch (IOException e) {

            return ResponseEntity.notFound().build();
        }
    }
}
