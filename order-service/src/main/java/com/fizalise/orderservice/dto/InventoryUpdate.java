package com.fizalise.orderservice.dto;

import lombok.Builder;

@Builder
public record InventoryUpdate(UpdateType updateType, String skuCode, Integer quantity) {
    public enum UpdateType {INCOME, EXPENSE}
}
