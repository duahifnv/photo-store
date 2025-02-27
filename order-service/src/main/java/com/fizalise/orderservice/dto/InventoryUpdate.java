package com.fizalise.orderservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record InventoryUpdate(
        @Schema(description = "Тип обновления учета (INCOME - приход товара, EXPENSE - расход товара",
        enumAsRef = true)
        UpdateType updateType,
        @Schema(description = "Код товара", example = "CAM-FUJIF-BL") String skuCode,
        @Schema(description = "Количество товара", example = "1") Integer quantity) {
    public enum UpdateType {INCOME, EXPENSE}
}
