package com.fizalise.inventoryservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record InventoryUpdate(
        @NotNull @Pattern(regexp = "INCOME|EXPENSE") String updateType,
        @NotBlank String skuCode,
        @Min(1) Integer quantity) {
}
