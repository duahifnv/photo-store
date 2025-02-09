package com.fizalise.inventoryservice.controller;

import com.fizalise.inventoryservice.entity.ProductCategory;
import com.fizalise.inventoryservice.entity.ProductItem;
import com.fizalise.inventoryservice.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public record ProductController(ProductService productService) {
    @GetMapping
    public List<ProductItem> getAllItems(
            @RequestParam(required = false) String categoryCode) {
        if (categoryCode != null) {
            return productService.findAllItemsByCategoryCode(categoryCode);
        }
        return productService.findAllItems();
    }
    @GetMapping("/categories")
    public List<ProductCategory> getAllCategories() {
        return productService.findAllCategories();
    }
}
