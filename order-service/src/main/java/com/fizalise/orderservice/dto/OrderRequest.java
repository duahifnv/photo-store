package com.fizalise.orderservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(@NotNull
                           @Schema(description = "Товары заказа")
                           List<OrderItemRequest> items) {
    public record OrderItemRequest(
            @NotNull
            @Schema(description = "Код товара", example = "CAM-FUJIF-BL")
            String skuCode,
            @NotNull @DecimalMin("0.0") @Digits(integer = 8, fraction = 2)
            @Schema(description = "Цена товара в рублях", example = "189990.00")
            BigDecimal price,
            @Schema(description = "Количество товара", example = "1")
            @NotNull @Min(1) Integer quantity
    ) {}
}
