package com.fizalise.imageservice.service;

import com.fizalise.imageservice.entity.ProductImage;
import com.fizalise.imageservice.exception.ResourceNotFoundException;
import com.fizalise.imageservice.repository.ProductImageRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
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
public class ProductImageService {
    private static final String IMAGE_PATH_PREFIX = "./static/images/item_img";
    private final ProductImageRepository productImageRepository;
    public byte[] getImageBytes(String skuCode) {
        return getImageBytes(getImageFilename(skuCode));
    }
    public ProductImage getImageFilename(String skuCode) {
        return productImageRepository.findBySkuCode(skuCode)
                .orElseThrow(ResourceNotFoundException::new);
    }
    public byte[] getImageBytes(ProductImage productImage) {
        try {
            String filename = productImage.getFilename();
            return Files.readAllBytes(getImagePath(filename));
        } catch (NoSuchFileException e) {
            throw new ResourceNotFoundException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private Path getImagePath(String filename) {
        try {
            Path fileClassPath = Path.of(IMAGE_PATH_PREFIX, filename);
            URL resourceUrl = Optional.ofNullable(
                    getClass().getClassLoader().getResource(fileClassPath.toString())
            ).orElseThrow(ResourceNotFoundException::new);
            return Paths.get(resourceUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
