package com.fizalise.inventoryservice.controller;

import com.fizalise.inventoryservice.dto.ProductRequest;
import com.fizalise.inventoryservice.entity.ProductCategory;
import com.fizalise.inventoryservice.entity.ProductItem;
import com.fizalise.inventoryservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductItem> getAllItems(
            @RequestParam(required = false) String categoryCode) {
        if (categoryCode != null) {
            return productService.findAllItemsByCategoryCode(categoryCode);
        }
        return productService.findAllItems();
    }
    @GetMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public ProductItem getItemBySkuCode(@PathVariable String skuCode) {
        return productService.findItemBySkuCode(skuCode);
    }
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductCategory> getAllCategories() {
        return productService.findAllCategories();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProduct(@Valid @RequestBody ProductRequest productRequest) {
        productService.createNewProduct(productRequest);
    }
    @DeleteMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductBySkuCode(@PathVariable String skuCode) {
        productService.deleteProductBySkuCode(skuCode);
    }
}
