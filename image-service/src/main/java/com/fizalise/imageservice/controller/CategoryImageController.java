package com.fizalise.imageservice.controller;

import com.fizalise.imageservice.entity.Image;
import com.fizalise.imageservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/v1/images/categories")
public class CategoryImageController implements ImageController {
    @Autowired
    @Qualifier("categoryImageService")
    private ImageService imageService;
    @GetMapping(value = "/{categoryCode}", produces = {"image/png"})
    @ResponseStatus(HttpStatus.OK)
    public byte[] getImage(@PathVariable String categoryCode) {
        return imageService.getImageBytes(categoryCode);
    }
    public Image addImage(@RequestParam("categoryCode") String categoryCode,
                          @RequestParam("imageFile") MultipartFile imageFile) {
        return imageService.addImage(categoryCode, imageFile);
    }
}
