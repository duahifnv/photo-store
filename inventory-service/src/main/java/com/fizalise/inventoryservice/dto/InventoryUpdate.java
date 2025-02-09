package com.fizalise.inventoryservice.dto;

public record InventoryUpdate(UpdateType updateType, String skuCode, Integer quantity) {
    public enum UpdateType {INCOME, EXPENSE}
}
