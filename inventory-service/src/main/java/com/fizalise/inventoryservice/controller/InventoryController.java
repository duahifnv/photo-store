package com.fizalise.inventoryservice.controller;

import com.fizalise.inventoryservice.entity.InventoryItem;
import com.fizalise.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public record InventoryController(InventoryService inventoryService) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryItem> getAllItems() {
        return inventoryService.findAllItems();
    }
}
