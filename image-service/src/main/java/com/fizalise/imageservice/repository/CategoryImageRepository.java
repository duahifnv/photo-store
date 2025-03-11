package com.fizalise.imageservice.repository;

import com.fizalise.imageservice.entity.CategoryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryImageRepository extends JpaRepository<CategoryImage, String> {
}
