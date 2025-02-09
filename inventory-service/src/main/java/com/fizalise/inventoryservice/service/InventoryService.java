package com.fizalise.inventoryservice.service;

import com.fizalise.inventoryservice.entity.InventoryItem;
import com.fizalise.inventoryservice.repository.InventoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record InventoryService(InventoryRepository inventoryRepository) {
    public List<InventoryItem> findAllItems() {
        return inventoryRepository.findAll(Sort.by(Sort.Direction.ASC,
                "skuCode"));
    }
}
