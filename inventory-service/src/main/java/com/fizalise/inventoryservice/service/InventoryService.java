package com.fizalise.inventoryservice.service;

import com.fizalise.inventoryservice.dto.InventoryUpdate;
import com.fizalise.inventoryservice.entity.InventoryItem;
import com.fizalise.inventoryservice.exception.BadRequestException;
import com.fizalise.inventoryservice.exception.ResourceNotFoundException;
import com.fizalise.inventoryservice.repository.InventoryRepository;
import com.fizalise.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    public List<InventoryItem> findAllItems() {
        return inventoryRepository.findAll(Sort.by(Sort.Direction.ASC,
                "skuCode"));
    }
    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository
                .existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }
    @Transactional
    public void updateInventory(InventoryUpdate inventoryUpdate) {
        Optional<InventoryItem> optionalInventoryItem = inventoryRepository
                .findById(inventoryUpdate.skuCode());
        if (optionalInventoryItem.isEmpty())  {
            if (!productRepository.existsById(inventoryUpdate.skuCode())) {
                throw new ResourceNotFoundException(
                        "Не найден продукт с SKU-code: " + inventoryUpdate.skuCode()
                );
            }
            if (inventoryUpdate.updateType() == InventoryUpdate.UpdateType.EXPENSE) {
                throw new BadRequestException(
                        "Невозможно выполнить расход: товара еще нет в наличии"
                );
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
                    throw new BadRequestException(
                            "Невозможно выполнить расход: в наличии товаров меньше, чем в расходе"
                    );
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
    @Transactional
    public void deleteItemBySkuCode(String skuCode) {
        if (!inventoryRepository.existsBySkuCode(skuCode)) {
            throw new ResourceNotFoundException("Не найден продукт с SKU-code: " + skuCode);
        }
        inventoryRepository.deleteBySkuCode(skuCode);
    }
}
