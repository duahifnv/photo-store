package com.fizalise.inventoryservice.mapper;

import com.fizalise.inventoryservice.dto.ProductRequest;
import com.fizalise.inventoryservice.entity.ProductItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Date;
import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = { LocalDate.class, Date.class } )
public interface ProductMapper {
    @Mapping(target = "releaseDate", expression = "java(Date.valueOf(LocalDate.now()))")
    ProductItem toProductItem(ProductRequest productRequest);
}
