package com.fizalise.orderservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record OrderRequest(@NotBlank
                           @Schema(description = "Код товара", example = "CAM-FUJIF-BL")
                           String skuCode,
                           @NotNull @DecimalMin("0.0") @Digits(integer = 8, fraction = 2)
                           @Schema(description = "Цена товара в рублях", example = "189990.00")
                           BigDecimal price,
                           @Schema(description = "Количество товара", example = "1")
                           @NotNull @Min(1) Integer quantity) {
}
