package com.fizalise.inventoryservice.controller;

import com.fizalise.inventoryservice.dto.InventoryUpdate;
import com.fizalise.inventoryservice.entity.InventoryItem;
import com.fizalise.inventoryservice.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class InventoryController {
    private final InventoryService inventoryService;
    @Operation(summary = "Получить все товары из учета")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryItem> getAllItems() {
        return inventoryService.findAllItems();
    }
    @Operation(summary = "Получить товар из учета по его коду")
    @GetMapping("/{skuCode}")
    public InventoryItem getItemBySkuCode(@PathVariable String skuCode) {
        return inventoryService.findItemBySkuCode(skuCode);
    }
    @Operation(summary = "Проверить, есть ли определенное количество товара в наличии",
            description = "Возвращает true, если товар есть в наличии, false в ином случае")
    @GetMapping("/in-stock")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }
    @Operation(summary = "Обновить товар в учете")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@Valid @RequestBody InventoryUpdate inventoryUpdate) {
        inventoryService.updateInventory(inventoryUpdate);
    }
    @Operation(summary = "Удалить товар из учета по его коду")
    @DeleteMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItemBySkuCode(@PathVariable String skuCode) {
        inventoryService.deleteItemBySkuCode(skuCode);
    }
}
