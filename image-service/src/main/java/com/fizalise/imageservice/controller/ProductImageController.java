package com.fizalise.imageservice.controller;

import com.fizalise.imageservice.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/images/products")
@RequiredArgsConstructor
public class ProductImageController {
    private final ProductImageService productImageService;
    @GetMapping(value = "/{skuCode}", produces = {"image/png", "image/jpeg"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public byte[] getImage(@PathVariable String skuCode) {
        return productImageService.getImageBytes(skuCode);
    }
}
