package com.fizalise.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "products", schema = "public", catalog = "inventory_service")
@Data
public class ProductItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sku_code")
    private String skuCode;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "category_code")
    private String categoryCode;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "release_date")
    private Date releaseDate;
}
