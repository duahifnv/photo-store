package com.fizalise.imageservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

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