package com.fizalise.imageservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category_image_filenames")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryImage extends Image {
    @Size(min = 3, max = 3)
    @Column(name = "id", nullable = false, length = 3)
    public String id;
    public CategoryImage(String id, String type, String filename) {
        super(id, type, filename);
        this.id = id;
    }
}