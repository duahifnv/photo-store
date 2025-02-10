package com.fizalise.inventoryservice.dto;

import java.math.BigDecimal;

public record ProductRequest(String skuCode, String name, String categoryCode, BigDecimal price) {
}
