package com.fizalise.inventoryservice.repository;

import com.fizalise.inventoryservice.entity.ProductItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductItem, String> {
    boolean existsBySkuCode(String skuCode);
    Optional<ProductItem> findBySkuCode(String skuCode);
    List<ProductItem> findAllByCategoryCode(String categoryCode, Sort sort);
    void deleteBySkuCode(String skuCode);
}
