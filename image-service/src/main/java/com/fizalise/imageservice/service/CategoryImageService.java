package com.fizalise.imageservice.service;

import com.fizalise.imageservice.entity.CategoryImage;
import com.fizalise.imageservice.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryImageService extends ImageService {
    private static final String folderPath = "static/images/categories";
    @Autowired
    protected CategoryImageService(@Qualifier("categoryImageRepository")
                                  JpaRepository<? extends Image, String> imageRepository) {
        super((JpaRepository<Image, String>) imageRepository, folderPath);
    }
    public Image saveImage(String imageId, String type, String filename) {
        return imageRepository.save(
                new CategoryImage(imageId, type, filename)
        );
    }
}
