package com.fizalise.orderservice.dto;

import com.fizalise.orderservice.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public record OrderResponse(
        @Schema(description = "Код заказа", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        UUID orderId,
        @Schema(description = "Почта заказчика", format = "email@domen.xx", example = "mail@mail.ru")
        String customerEmail,
        @Schema(description = "Время оформления заказа", format = "yyyy-mm-dd hh:mm:ss")
        Timestamp orderTimestamp,
        @Schema(description = "Товары заказа")
        List<OrderItemResponse> items,
        @Schema(description = "Имя заказчика")
        String name,
        @Schema(description = "Фамилия заказчика")
        String surname
) {
    public record OrderItemResponse(
         @Schema(description = "Код элемента заказа", example = "1")
         Long itemId,
         @Schema(description = "UUID заказа")
        UUID orderId,
        @Schema(description = "Код товара", example = "CAM-FUJIF-BL")
        String skuCode,
        @Schema(description = "Цена товара в рублях", example = "189990.00")
        BigDecimal price,
        @Schema(description = "Количество товара", example = "1")
        Integer quantity
    ) {}
}
