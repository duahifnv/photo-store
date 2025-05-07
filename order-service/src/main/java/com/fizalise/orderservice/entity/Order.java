package com.fizalise.orderservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
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
    @Column(name = "customer_email")
    @Schema(description = "Почта заказчика", format = "email@domen.xx",
            example = "mail@mail.ru")
    private String customerEmail;
    @Basic
    @Column(name = "order_timestamp")
    @Schema(description = "Время оформления заказа", format = "yyyy-mm-dd hh:mm:ss")
    private Timestamp orderTimestamp;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    List<OrderItem> items;
    @Transient
    @Schema(description = "Имя заказчика")
    private String name;
    @Transient
    @Schema(description = "Фамилия заказчика")
    private String surname;
}
