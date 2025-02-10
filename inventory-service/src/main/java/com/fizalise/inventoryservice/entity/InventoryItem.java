package com.fizalise.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory", schema = "public", catalog = "inventory_service")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItem {
    @Id
    @Column(name = "sku_code")
    private String skuCode;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
}
