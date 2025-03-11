package com.fizalise.imageservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Image {
    @Id
    public String id;
    @Size(max = 50)
    @NotNull
    @Column(name = "image_type", nullable = false, length = 50)
    public String type;
    @Size(max = 200)
    @NotNull
    @Column(name = "image_filename", nullable = false, length = 200)
    public String filename;
}
