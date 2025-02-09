package com.fizalise.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Table(name = "inventory", schema = "public", catalog = "inventory_service")
@Data
public class InventoryItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sku_code")
    private String skuCode;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
}
