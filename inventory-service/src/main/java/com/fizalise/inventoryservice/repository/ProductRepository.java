package com.fizalise.inventoryservice.repository;

import com.fizalise.inventoryservice.entity.ProductItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductItem, String> {
    List<ProductItem> findAllByCategoryCode(String categoryCode, Sort sort);
}
