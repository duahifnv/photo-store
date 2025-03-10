package com.fizalise.imageservice.controller;

import org.springframework.web.multipart.MultipartFile;

public interface ImageController {
    byte[] getImage(String imageId);
    void uploadImage(String imageId, MultipartFile imageFile);
    void updateImage(String imageId, MultipartFile imageFile);
    void removeImage(String imageId);
}