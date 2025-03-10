package com.fizalise.imageservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_image_filenames")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {
    @Id
    @Size(max = 100)
    @Column(name = "sku_code", nullable = false, length = 100)
    private String skuCode;
    @Size(max = 50)
    @NotNull
    @Column(name = "image_type", nullable = false, length = 50)
    private String type;
    @Size(max = 200)
    @NotNull
    @Column(name = "image_filename", nullable = false, length = 200)
    private String filename;
}