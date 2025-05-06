package com.fizalise.orderservice.mapper;

import com.fizalise.orderservice.client.dto.UserInfo;
import com.fizalise.orderservice.dto.OrderRequest;
import com.fizalise.orderservice.dto.OrderResponse;
import com.fizalise.orderservice.entity.Order;
import com.fizalise.orderservice.entity.OrderItem;
import com.fizalise.orderservice.event.OrderPlacedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring",
        imports = {UUID.class, Timestamp.class, Instant.class})
public interface OrderMapper {

    @Mapping(target = "orderId", expression = "java(UUID.randomUUID())")
    @Mapping(target = "orderTimestamp", expression = "java(Timestamp.from(Instant.now()))")
    @Mapping(source = "userInfo.email", target = "customerEmail")
    @Mapping(source = "userInfo.name", target = "name")
    @Mapping(source = "userInfo.surname", target = "surname")
    @Mapping(target = "items", ignore = true)
    Order toOrder(OrderRequest orderRequest, UserInfo userInfo);

    OrderItem toItem(OrderRequest.OrderItemRequest itemRequest);

    @Mapping(source = "customerEmail", target = "email")
    @Mapping(source = "orderId", target = "orderId", qualifiedByName = "idToSequence")
    OrderPlacedEvent toEvent(Order order);

    OrderResponse toResponse(Order order);

    OrderResponse.OrderItemResponse toItemResponse(OrderItem item);

    @Named("idToSequence")
    default CharSequence map(UUID value) {
        return value.toString();
    }

    default List<OrderResponse.OrderItemResponse> mapItems(List<OrderItem> items) {
        return items.stream()
                .map(this::toItemResponse)
                .toList();
    }
}
