package com.fizalise.imageservice.controller;

import com.fizalise.imageservice.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    @GetMapping(value = "/products/{skuCode}", produces = {"image/png", "image/jpeg"})
    @ResponseStatus(HttpStatus.OK)
    public byte[] getProductImage(@PathVariable String skuCode) {
        return imageService.getImageBytes(skuCode, ImageService.ImageType.PRODUCT);
    }
    @GetMapping(value = "/categories/{categoryCode}", produces = {"image/png"})
    @ResponseStatus(HttpStatus.OK)
    public byte[] getCategoryImage(@PathVariable String categoryCode) {
        return imageService.getImageBytes(categoryCode, ImageService.ImageType.CATEGORY);
    }
}
