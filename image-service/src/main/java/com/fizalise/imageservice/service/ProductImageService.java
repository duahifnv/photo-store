package com.fizalise.imageservice.service;

import com.fizalise.imageservice.entity.Image;
import com.fizalise.imageservice.entity.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService extends ImageService {
    private static final String folderPath = "static/images/products";
    @Autowired
    protected ProductImageService(@Qualifier("productImageRepository")
                                  JpaRepository<? extends Image, String> imageRepository) {
        super((JpaRepository<Image, String>) imageRepository, folderPath);
    }
    @Override
    public Image saveImage(String imageId, String type, String filename) {
        return imageRepository.save(
                new ProductImage(imageId, type, filename)
        );
    }
}
