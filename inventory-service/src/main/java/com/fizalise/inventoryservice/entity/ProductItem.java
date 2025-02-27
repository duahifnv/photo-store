package com.fizalise.inventoryservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "products", schema = "public", catalog = "inventory_service")
@Data
public class ProductItem {
    @Id
    @Column(name = "sku_code")
    @Schema(description = "Код товара", example = "CAM-FUJIF-BL")
    private String skuCode;
    @Basic
    @Column(name = "name")
    @Schema(description = "Наименование товара", example = "Фотоаппарат Fujifilm X-T5 body черный")
    private String name;
    @Basic
    @Column(name = "category_code")
    @Schema(description = "Код категории товара", example = "CAM")
    private String categoryCode;
    @Basic
    @Column(name = "price")
    @Schema(description = "Цена товара в рублях", example = "189990.00")
    private BigDecimal price;
    @Basic
    @Column(name = "release_date")
    @Schema(description = "Дата создания товара", format = "yyyy-mm-dd", example = "2025-02-27")
    private Date releaseDate;
}
