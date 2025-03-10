package com.fizalise.imageservice.controller;

import com.fizalise.imageservice.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/images/categories")
@RequiredArgsConstructor
public class CategoryImageController implements ImageController {
    private final ImageService imageService;
    @GetMapping(value = "/{categoryCode}", produces = {"image/png"})
    @ResponseStatus(HttpStatus.OK)
    public byte[] getCategoryImage(@PathVariable String categoryCode) {
        return imageService.getImageBytes(categoryCode, ImageService.ImageType.CATEGORY);
    }
}
