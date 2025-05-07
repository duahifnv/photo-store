package com.fizalise.imageservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_image_filenames")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage extends Image {
    @Size(max = 100)
    @Column(name = "id", nullable = false, length = 100)
    private String id;
    public ProductImage(String id, String type, String filename) {
        super(id, type, filename);
        this.id = id;
    }
}