package com.fizalise.orderservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "public", catalog = "order_service")
@Data
public class Order {
    @Id
    @Column(name = "order_id")
    @Schema(description = "Код заказа", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID orderId;
    @Basic
    @Column(name = "sku_code")
    @Schema(description = "Код товара", example = "CAM-FUJIF-BL")
    private String skuCode;
    @Basic
    @Column(name = "price")
    @Schema(description = "Цена товара в рублях", example = "189990.00")
    private BigDecimal price;
    @Basic
    @Column(name = "quantity")
    @Schema(description = "Количество товара", example = "1")
    private Integer quantity;
    @Basic
    @Column(name = "customer_email")
    @Schema(description = "Почта заказчика", format = "email@domen.xx",
            example = "mail@mail.ru")
    private String customerEmail;
    @Basic
    @Column(name = "order_timestamp")
    @Schema(description = "Время оформления заказа", format = "yyyy-mm-dd hh:mm:ss")
    private Timestamp orderTimestamp;
    @Transient
    @Schema(description = "Имя заказчика")
    private String name;
    @Transient
    @Schema(description = "Фамилия заказчика")
    private String surname;
}
