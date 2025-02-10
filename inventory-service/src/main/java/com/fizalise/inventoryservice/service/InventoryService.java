package com.fizalise.inventoryservice.service;

import com.fizalise.inventoryservice.dto.InventoryUpdate;
import com.fizalise.inventoryservice.entity.InventoryItem;
import com.fizalise.inventoryservice.exception.BadRequestException;
import com.fizalise.inventoryservice.exception.ResourceNotFoundException;
import com.fizalise.inventoryservice.repository.InventoryRepository;
import com.fizalise.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    public List<InventoryItem> findAllItems() {
        return inventoryRepository.findAll(Sort.by(Sort.Direction.ASC,
                "skuCode"));
    }
    public InventoryItem findItemBySkuCode(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(ResourceNotFoundException::new);
    }
    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository
                .existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }
    @Transactional
    public void updateInventory(InventoryUpdate inventoryUpdate) {
        Optional<InventoryItem> optionalInventoryItem = inventoryRepository
                .findById(inventoryUpdate.skuCode());
        InventoryItem inventoryItem;
        if (optionalInventoryItem.isEmpty())  {
            if (!productRepository.existsById(inventoryUpdate.skuCode())) {
                throw new ResourceNotFoundException();
            }
            if (inventoryUpdate.updateType() == InventoryUpdate.UpdateType.EXPENSE) {
                throw new BadRequestException(
                        "Невозможно выполнить расход: товара еще нет в наличии"
                );
            }
            inventoryItem = InventoryItem.builder()
                    .skuCode(inventoryUpdate.skuCode())
                    .quantity(inventoryUpdate.quantity())
                    .build();
            log.info("Добавлен новый товар: {}", inventoryItem);
        }
        else {
            inventoryItem = optionalInventoryItem.get();
            if (inventoryUpdate.updateType() == InventoryUpdate.UpdateType.EXPENSE) {
                if (inventoryUpdate.quantity() > inventoryItem.getQuantity()) {
                    throw new BadRequestException(
                            "Невозможно выполнить расход: в наличии товаров меньше, чем в расходе"
                    );
                }
                inventoryItem.setQuantity(inventoryItem.getQuantity() - inventoryUpdate.quantity());
            }
            else {
                inventoryItem.setQuantity(inventoryItem.getQuantity() + inventoryUpdate.quantity());
            }
            log.info("Изменено количество товара {}: {}",
                    inventoryItem.getSkuCode(), inventoryItem.getQuantity());
        }
        inventoryRepository.save(inventoryItem);
        log.info("Товар {} сохранен в базу данных", inventoryItem.getSkuCode());
    }
    @Transactional
    public void deleteItemBySkuCode(String skuCode) {
        if (!inventoryRepository.existsBySkuCode(skuCode)) {
            throw new ResourceNotFoundException();
        }
        inventoryRepository.deleteBySkuCode(skuCode);
        log.info("Товар {} удален из базы данных", skuCode);
    }
}
