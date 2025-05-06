package com.fizalise.orderservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "orders_items", schema = "public", catalog = "order_service")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    @Schema(description = "Код элемента заказа", example = "1")
    private Long itemId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @Schema(description = "Заказ")
    private Order order;
    @Basic
    @Column(name = "sku_code", nullable = false)
    @Schema(description = "Код товара", example = "CAM-FUJIF-BL")
    private String skuCode;
    @Basic
    @Column(name = "price", nullable = false)
    @Schema(description = "Цена товара в рублях", example = "189990.00")
    private BigDecimal price;
    @Basic
    @Column(name = "quantity", nullable = false)
    @Schema(description = "Количество товара", example = "1")
    private Integer quantity;
}
