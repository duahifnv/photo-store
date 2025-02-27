package com.fizalise.inventoryservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(@NotBlank
                             @Schema(description = "Код товара", example = "CAM-FUJIF-BL")
                             String skuCode,
                             @NotBlank
                             @Schema(description = "Наименование товара",
                             example = "Фотоаппарат Fujifilm X-T5 body черный") String name,
                             @NotBlank @Size(min = 3, max = 3, message = "длина должна быть равна 3")
                             @Schema(description = "Код категории товара", example = "CAM")
                             String categoryCode,
                             @NotNull @DecimalMin("0.0") @Digits(integer = 8, fraction = 2)
                             @Schema(description = "Цена товара в рублях", example = "189990.00")
                             BigDecimal price) {
}
