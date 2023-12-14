package techno.hub.backend.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import techno.hub.backend.configuration.StorageConfiguration;
import techno.hub.backend.exceptions.FileStorageException;
import techno.hub.backend.services.StorageService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    public StorageServiceImpl(StorageConfiguration properties) {
        this.rootLocation = Paths.get(properties.getLocation()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.rootLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String store(MultipartFile file) {
        String contentType = file.getContentType();

        if (contentType != null &&
                !contentType.startsWith("image/")) {
            throw new FileStorageException("Invalid file type. Only images are allowed.");
        }

        String fileName = LocalDateTime.now().toString().replace(':', '_') + "." + FilenameUtils.getExtension(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.rootLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new IllegalArgumentException(
                        "Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteByName(String filename) {
        log.info("delete file by name: {}", filename);
    }

}
