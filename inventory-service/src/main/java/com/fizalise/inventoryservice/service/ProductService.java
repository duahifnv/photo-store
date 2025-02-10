package com.fizalise.inventoryservice.service;

import com.fizalise.inventoryservice.dto.ProductRequest;
import com.fizalise.inventoryservice.entity.ProductCategory;
import com.fizalise.inventoryservice.entity.ProductItem;
import com.fizalise.inventoryservice.exception.BadRequestException;
import com.fizalise.inventoryservice.exception.ResourceNotFoundException;
import com.fizalise.inventoryservice.mapper.ProductMapper;
import com.fizalise.inventoryservice.repository.ProductCategoryRepository;
import com.fizalise.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    public List<ProductItem> findAllItems() {
        return productRepository.findAll(
                Sort.by(Sort.Direction.ASC, "price")
        );
    }
    public List<ProductItem> findAllItemsByCategoryCode(String categoryCode) {
        if (categoryRepository.findById(categoryCode).isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return productRepository.findAllByCategoryCode(
                categoryCode,
                Sort.by(Sort.Direction.ASC, "price")
        );
    }
    public ProductItem findItemBySkuCode(String skuCode) {
        return productRepository.findBySkuCode(skuCode)
                .orElseThrow(ResourceNotFoundException::new);
    }
    public List<ProductCategory> findAllCategories() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
    @Transactional
    public void createNewProduct(ProductRequest productRequest) {
        if (productRepository.existsBySkuCode(productRequest.skuCode())) {
            throw new BadRequestException("Уже существует продукт с SKU-code: " +
                    productRequest.skuCode());
        }
        if (!categoryRepository.existsByCategoryCode(productRequest.categoryCode())) {
            throw new BadRequestException("Не найдена продуктовая категория: " +
                    productRequest.categoryCode());
        }
        ProductItem productItem = productMapper.toProductItem(productRequest);
        log.info("Создан новый продукт: {}", productRequest.skuCode());
        productRepository.save(productItem);
        log.info("Продукт {} сохранен в базу данных", productRequest.skuCode());
    }
    @Transactional
    public void deleteProductBySkuCode(String skuCode) {
        if (!productRepository.existsBySkuCode(skuCode)) {
            throw new ResourceNotFoundException();
        }
        productRepository.deleteBySkuCode(skuCode);
        log.info("Продукт {} удален из базы данных", skuCode);
    }
}
