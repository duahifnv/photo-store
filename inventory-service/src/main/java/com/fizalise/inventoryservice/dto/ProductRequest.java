package com.fizalise.inventoryservice.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record ProductRequest(@NotBlank String skuCode,
                             @NotBlank String name,
                             @NotBlank @Size(min = 3, max = 3, message = "длина должна быть равна 3") String categoryCode,
                             @NotNull @DecimalMin("0.0") @Digits(integer = 8, fraction = 2) BigDecimal price) {
}
