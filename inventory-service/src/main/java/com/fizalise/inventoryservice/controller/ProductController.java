package com.fizalise.inventoryservice.controller;

import com.fizalise.inventoryservice.dto.ProductRequest;
import com.fizalise.inventoryservice.entity.ProductCategory;
import com.fizalise.inventoryservice.entity.ProductItem;
import com.fizalise.inventoryservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/product-list")
public record ProductController(ProductService productService) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductItem> getAllItems(
            @RequestParam(required = false) String categoryCode) {
        if (categoryCode != null) {
            return productService.findAllItemsByCategoryCode(categoryCode);
        }
        return productService.findAllItems();
    }
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductCategory> getAllCategories() {
        return productService.findAllCategories();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProduct(@RequestBody ProductRequest productRequest) {
        productService.createNewProduct(productRequest);
    }
}
