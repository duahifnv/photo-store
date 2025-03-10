package com.fizalise.imageservice.controller;

import com.fizalise.imageservice.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/images/products")
@RequiredArgsConstructor
public class ProductImageController implements ImageController {
    protected final ImageService imageService;
    @GetMapping(value = "/{skuCode}", produces = {"image/png", "image/jpeg"})
    @ResponseStatus(HttpStatus.OK)
    public byte[] getImage(@PathVariable String skuCode) {
        return imageService.getImageBytes(skuCode, ImageService.ImageType.PRODUCT);
    }
}
