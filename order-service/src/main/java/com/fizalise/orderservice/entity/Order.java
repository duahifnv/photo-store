package com.fizalise.orderservice.entity;

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
    private UUID orderId;
    @Basic
    @Column(name = "sku_code")
    private String skuCode;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "customer_email")
    private String customerEmail;
    @Basic
    @Column(name = "order_timestamp")
    private Timestamp orderTimestamp;
    @Transient
    private String firstName;
    @Transient
    private String lastName;
}
