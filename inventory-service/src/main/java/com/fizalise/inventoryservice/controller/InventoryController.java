package com.fizalise.inventoryservice.controller;

import com.fizalise.inventoryservice.dto.InventoryUpdate;
import com.fizalise.inventoryservice.entity.InventoryItem;
import com.fizalise.inventoryservice.service.InventoryService;
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
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryItem> getAllItems() {
        return inventoryService.findAllItems();
    }
    @GetMapping("/{skuCode}")
    public InventoryItem getItemBySkuCode(@PathVariable String skuCode) {
        return inventoryService.findItemBySkuCode(skuCode);
    }
    @GetMapping("/in-stock")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@Valid @RequestBody InventoryUpdate inventoryUpdate) {
        inventoryService.updateInventory(inventoryUpdate);
    }
    @DeleteMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItemBySkuCode(@PathVariable String skuCode) {
        inventoryService.deleteItemBySkuCode(skuCode);
    }
}
