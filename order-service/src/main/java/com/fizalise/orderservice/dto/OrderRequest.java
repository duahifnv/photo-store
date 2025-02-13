package com.fizalise.orderservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record OrderRequest(@NotBlank String skuCode,
                           @NotNull @DecimalMin("0.0") @Digits(integer = 8, fraction = 2) BigDecimal price,
                           @NotNull @Min(1) Integer quantity,
                           @Valid @NotNull UserDetails userDetails) {
    public record UserDetails(@NotNull @Email String email,
                              String firstName,
                              String lastName) {}
}
