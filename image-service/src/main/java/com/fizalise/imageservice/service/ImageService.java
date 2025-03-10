package com.fizalise.imageservice.service;

import com.fizalise.imageservice.exception.ResourceNotFoundException;
import com.fizalise.imageservice.repository.CategoryImageRepository;
import com.fizalise.imageservice.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ProductImageRepository productImageRepository;
    private final CategoryImageRepository categoryImageRepository;
    public enum ImageType {
        PRODUCT("static/images/products"),
        CATEGORY("static/images/categories");
        public final String directoryPath;
        ImageType(String directoryPath) {
            this.directoryPath = directoryPath;
        }
    }
    public byte[] getImageBytes(String imageId, ImageType imageType) {
        String filename = getImageFilename(imageId, imageType);
        Path imagePath = getImagePath(filename, imageType);
        return getImageBytes(imagePath);
    }
    private byte[] getImageBytes(Path imagePath) {
        try {
            return Files.readAllBytes(imagePath);
        } catch (NoSuchFileException e) {
            throw new ResourceNotFoundException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getImageFilename(String imageId, ImageType imageType) {
        switch (imageType) {
            case PRODUCT -> {
                return productImageRepository.findBySkuCode(imageId)
                        .orElseThrow(ResourceNotFoundException::new).getFilename();
            }
            case CATEGORY -> {
                return categoryImageRepository.findByCategoryCode(imageId)
                        .orElseThrow(ResourceNotFoundException::new).getFilename();
            }
            default -> throw new IllegalArgumentException();
        }
    }
    private Path getImagePath(String filename, ImageType imageType) {
        try {
            Path fileClassPath = Path.of(imageType.directoryPath, filename);
            URL resourceUrl = Optional.ofNullable(
                    getClass().getClassLoader().getResource(fileClassPath.toString())
            ).orElseThrow(ResourceNotFoundException::new);
            return Paths.get(resourceUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
