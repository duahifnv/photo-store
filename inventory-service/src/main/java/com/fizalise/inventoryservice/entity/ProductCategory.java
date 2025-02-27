package com.fizalise.inventoryservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_categories", schema = "public", catalog = "inventory_service")
@Data
public class ProductCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_code")
    @Schema(description = "Код категории товара", example = "CAM")
    private String categoryCode;
    @Basic
    @Column(name = "name")
    @Schema(description = "Наименование категории", example = "Фотоаппараты")
    private String name;
    @Basic
    @Column(name = "description")
    @Schema(description = "Описание категории")
    private String description;
}
