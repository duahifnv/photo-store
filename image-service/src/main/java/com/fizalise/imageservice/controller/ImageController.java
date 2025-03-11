package com.fizalise.imageservice.controller;

import com.fizalise.imageservice.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageController {
    byte[] getImage(String imageId);
    Image addImage(String imageId, MultipartFile imageFile);
}