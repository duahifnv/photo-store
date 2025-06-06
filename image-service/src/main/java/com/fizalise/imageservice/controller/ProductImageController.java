package com.fizalise.imageservice.controller;

import com.fizalise.imageservice.entity.Image;
import com.fizalise.imageservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/v1/images/products")
public class ProductImageController implements ImageController {
    @Autowired
    @Qualifier("productImageService")
    private ImageService imageService;
    @GetMapping(value = "/{skuCode}", produces = {"image/png", "image/jpeg"})
    @ResponseStatus(HttpStatus.OK)
    public byte[] getImage(@PathVariable String skuCode) {
        return imageService.getImageBytes(skuCode);
    }
    @PostMapping(value = "/{skuCode}",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public Image addImage(@PathVariable String skuCode,
                          @RequestPart("imageFile") MultipartFile imageFile) {
        return imageService.addImage(skuCode, imageFile);
    }
}
