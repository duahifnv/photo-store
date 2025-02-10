package com.fizalise.inventoryservice.repository;

import com.fizalise.inventoryservice.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, String> {
    boolean existsBySkuCode(String skuCode);
    boolean existsBySkuCodeAndQuantityGreaterThanEqual(String skuCode, Integer quantity);
    Optional<InventoryItem> findBySkuCode(String skuCode);
    void deleteBySkuCode(String skuCode);
}
