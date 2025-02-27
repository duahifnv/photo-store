package com.fizalise.inventoryservice.controller;

import com.fizalise.inventoryservice.dto.ProductRequest;
import com.fizalise.inventoryservice.entity.ProductCategory;
import com.fizalise.inventoryservice.entity.ProductItem;
import com.fizalise.inventoryservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @Operation(summary = "Получить все товары")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductItem> getAllItems(
            @RequestParam(required = false) String categoryCode) {
        if (categoryCode != null) {
            return productService.findAllItemsByCategoryCode(categoryCode);
        }
        return productService.findAllItems();
    }
    @Operation(summary = "Получить товар по его коду")
    @GetMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public ProductItem getItemBySkuCode(@PathVariable String skuCode) {
        return productService.findItemBySkuCode(skuCode);
    }
    @Operation(summary = "Получить все категории товаров")
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductCategory> getAllCategories() {
        return productService.findAllCategories();
    }
    @Operation(summary = "Создать новый товар")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProduct(@Valid @RequestBody ProductRequest productRequest) {
        productService.createNewProduct(productRequest);
    }
    @Operation(summary = "Удалить товар по его коду")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductBySkuCode(@PathVariable String skuCode) {
        productService.deleteProductBySkuCode(skuCode);
    }
}
