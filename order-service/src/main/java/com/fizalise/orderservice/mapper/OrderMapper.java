package com.fizalise.orderservice.mapper;

import com.fizalise.orderservice.dto.CreatedOrder;
import com.fizalise.orderservice.dto.OrderRequest;
import com.fizalise.orderservice.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface OrderMapper {
    @Mapping(target = "orderId", expression = "java(UUID.randomUUID())")
    Order toOrder(OrderRequest orderRequest, String customerEmail);
    @Mapping(source = "orderTimestamp", target = "timestamp")
    CreatedOrder toCreatedOrder(Order order);
}
