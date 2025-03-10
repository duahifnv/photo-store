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
@Table(name = "category_image_filenames")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryImage {
    @Id
    @Size(min = 3, max = 3)
    @Column(name = "category_code", nullable = false, length = 3)
    public String categoryCode;
    @Size(max = 50)
    @NotNull
    @Column(name = "image_type", nullable = false, length = 50)
    private String type;
    @Size(max = 200)
    @NotNull
    @Column(name = "image_filename", nullable = false, length = 200)
    private String filename;
}