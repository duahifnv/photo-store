package com.fizalise.imageservice.service;

import com.fizalise.imageservice.entity.Image;
import com.fizalise.imageservice.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public abstract class ImageService {
    protected final JpaRepository<Image, String> imageRepository;
    protected final String folderPath;
    protected ImageService(JpaRepository<Image, String> imageRepository,
                           String folderPath) {
        this.imageRepository = imageRepository;
        this.folderPath = folderPath;
    }
    public byte[] getImageBytes(String imageId) {
        Image imageEntity = getImage(imageId);
        return getImageBytes(folderPath, imageEntity.getFilename());
    }
    public Image getImage(String imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(ResourceNotFoundException::new);
    }
    public Image addImage(String imageId, MultipartFile imageFile) {
        String filename = imageFile.getOriginalFilename();
        try {
            Path imagePath = Path.of(folderPath, filename);
            imageFile.transferTo(new File(getAbsoluteImagePath(imagePath).toString()));
            return saveImage(imageId, imageFile.getContentType(), filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public abstract Image saveImage(String imageId, String type, String filename);
    protected byte[] getImageBytes(String folderPath, String filename) {
        try {
            Path path = getAbsoluteImagePath(Path.of(folderPath, filename));
            return Files.readAllBytes(path);
        } catch (NoSuchFileException e) {
            throw new ResourceNotFoundException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected Path getAbsoluteImagePath(Path relativePath) {
        try {
            URL resourceUrl = Optional.ofNullable(
                    getClass().getClassLoader().getResource(relativePath.toString())
            ).orElseThrow(ResourceNotFoundException::new);
            return Paths.get(resourceUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}