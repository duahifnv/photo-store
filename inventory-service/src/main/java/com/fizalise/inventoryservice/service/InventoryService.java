package com.fizalise.inventoryservice.service;

import com.fizalise.inventoryservice.dto.InventoryUpdate;
import com.fizalise.inventoryservice.entity.InventoryItem;
import com.fizalise.inventoryservice.repository.InventoryRepository;
import com.fizalise.inventoryservice.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public record InventoryService(InventoryRepository inventoryRepository,
                               ProductRepository productRepository) {
    public List<InventoryItem> findAllItems() {
        return inventoryRepository.findAll(Sort.by(Sort.Direction.ASC,
                "skuCode"));
    }
    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository
                .existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }
    public void updateInventory(InventoryUpdate inventoryUpdate) {
        Optional<InventoryItem> optionalInventoryItem = inventoryRepository
                .findById(inventoryUpdate.skuCode());
        if (optionalInventoryItem.isEmpty())  {
            if (!productRepository.existsById(inventoryUpdate.skuCode())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Не найден продукт с SKU-code: " + inventoryUpdate.skuCode());
            }
            if (inventoryUpdate.updateType() == InventoryUpdate.UpdateType.EXPENSE) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Невозможно выполнить расход: товара еще нет в наличии");
            }
            InventoryItem inventoryItem = InventoryItem.builder()
                    .skuCode(inventoryUpdate.skuCode())
                    .quantity(inventoryUpdate.quantity())
                    .build();
            inventoryRepository.save(inventoryItem);
        }
        else {
            InventoryItem inventoryItem = optionalInventoryItem.get();
            if (inventoryUpdate.updateType() == InventoryUpdate.UpdateType.EXPENSE) {
                if (inventoryUpdate.quantity() > inventoryItem.getQuantity()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Невозможно выполнить расход: в наличии товаров меньше, чем в расходе");
                }
                inventoryItem.setQuantity(inventoryItem.getQuantity() -
                        inventoryUpdate.quantity());
            }
            else {
                inventoryItem.setQuantity(inventoryItem.getQuantity() +
                        inventoryUpdate.quantity());
            }
            inventoryRepository.save(inventoryItem);
        }
    }
}
