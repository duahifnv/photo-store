package com.fizalise.inventoryservice.controller;

import com.fizalise.inventoryservice.dto.InventoryUpdate;
import com.fizalise.inventoryservice.entity.InventoryItem;
import com.fizalise.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public record InventoryController(InventoryService inventoryService) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryItem> getAllItems() {
        return inventoryService.findAllItems();
    }
    @GetMapping("/in-stock")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@RequestBody InventoryUpdate inventoryUpdate) {
        inventoryService.updateInventory(inventoryUpdate);
    }
}
