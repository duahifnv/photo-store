package com.fizalise.inventoryservice.service;

import com.fizalise.inventoryservice.entity.ProductCategory;
import com.fizalise.inventoryservice.entity.ProductItem;
import com.fizalise.inventoryservice.repository.ProductCategoryRepository;
import com.fizalise.inventoryservice.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public record ProductService(ProductRepository productRepository,
                             ProductCategoryRepository categoryRepository) {
    public List<ProductItem> findAllItems() {
        return productRepository.findAll(
                Sort.by(Sort.Direction.ASC, "price")
        );
    }
    public List<ProductItem> findAllItemsByCategoryCode(String categoryCode) {
        if (categoryRepository.findById(categoryCode).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Ресурс не найден");
        }
        return productRepository.findAllByCategoryCode(
                categoryCode,
                Sort.by(Sort.Direction.ASC, "price")
        );
    }
    public List<ProductCategory> findAllCategories() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
}
