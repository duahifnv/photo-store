package com.fizalise.inventoryservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Код товара", example = "CAM-FUJIF-BL")
    private String skuCode;
    @Basic
    @Column(name = "quantity")
    @Schema(description = "Количество товара", example = "1")
    private Integer quantity;
}
