package com.fizalise.orderservice.mapper;

import com.fizalise.orderservice.dto.CreatedOrder;
import com.fizalise.orderservice.dto.OrderRequest;
import com.fizalise.orderservice.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = { UUID.class, Timestamp.class, Instant.class } )
public interface OrderMapper {
    @Mapping(target = "orderId", expression = "java(UUID.randomUUID())")
    @Mapping(target = "orderTimestamp", expression = "java(Timestamp.from(Instant.now()))")
    Order toOrder(OrderRequest orderRequest, String customerEmail);
    CreatedOrder toCreatedOrder(Order order);
}
