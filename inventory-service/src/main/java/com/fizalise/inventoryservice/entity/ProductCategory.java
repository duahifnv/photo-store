package com.fizalise.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_categories", schema = "public", catalog = "inventory_service")
@Data
public class ProductCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_code")
    private String categoryCode;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
}
