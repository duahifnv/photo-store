package com.fizalise.orderservice.client;

import com.fizalise.orderservice.dto.InventoryUpdate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PutExchange;

public interface InventoryClient {
    @PutExchange("/api/v1/inventory")
    void updateInventory(@RequestBody InventoryUpdate inventoryUpdate);
}
