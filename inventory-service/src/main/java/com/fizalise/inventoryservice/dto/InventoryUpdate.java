package com.fizalise.inventoryservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record InventoryUpdate(
        @NotNull @Pattern(regexp = "INCOME|EXPENSE")
        @Schema(description = "Тип обновления учета (INCOME - приход товара, EXPENSE - расход товара")
        String updateType,
        @NotBlank
        @Schema(description = "Код товара", example = "CAM-FUJIF-BL") String skuCode,
        @Min(1)
        @Schema(description = "Количество товара", example = "1") Integer quantity) {
}
