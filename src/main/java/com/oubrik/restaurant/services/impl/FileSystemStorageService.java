package com.oubrik.restaurant.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.oubrik.restaurant.exceptions.StorageException;
import com.oubrik.restaurant.services.StorageService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileSystemStorageService implements StorageService {

    @Value("${app.storage.location:uploads}")
    private String storageLocation;
    private Path rootLocation;

    @PostConstruct
    public void init() {
        rootLocation = Path.of(storageLocation);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }

    @Override
    public String store(MultipartFile file, String fileName) {
        try {

            if (file.isEmpty()) {
                throw new StorageException("Cannot save an empty file");
            }
            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String finalFileName = fileName + "." + extension;

            Path destinationFile = rootLocation.resolve(Path.of(finalFileName)).normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store files outside specified directory");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return finalFileName;
        } catch (IOException e) {
            throw new StorageException("Failed to store file", e);
        }
    }

    @Override
    public Optional<Resource> loadAsResource(String fileName) {
        try {
            Path filePath = rootLocation.resolve(fileName);
            UrlResource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return Optional.of(resource);
            }
        } catch (MalformedURLException e) {
            log.warn("Could not read file: {}", fileName, e);
        }
        return Optional.empty();
    }

}