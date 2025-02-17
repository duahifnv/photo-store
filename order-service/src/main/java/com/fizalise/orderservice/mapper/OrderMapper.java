package com.fizalise.orderservice.mapper;

import com.fizalise.orderservice.client.dto.UserInfo;
import com.fizalise.orderservice.dto.OrderRequest;
import com.fizalise.orderservice.entity.Order;
import com.fizalise.orderservice.event.OrderPlacedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = { UUID.class, Timestamp.class, Instant.class } )
public interface OrderMapper {
    @Mapping(target = "orderId", expression = "java(UUID.randomUUID())")
    @Mapping(target = "orderTimestamp", expression = "java(Timestamp.from(Instant.now()))")
    @Mapping(source = "userInfo.email", target = "customerEmail")
    @Mapping(source = "userInfo.name", target = "name")
    @Mapping(source = "userInfo.surname", target = "surname")
    Order toOrder(OrderRequest orderRequest, UserInfo userInfo);
    @Mapping(source = "customerEmail", target = "email")
    @Mapping(source = "orderId", target = "orderId", qualifiedByName = "idToSequence")
    OrderPlacedEvent toEvent(Order order);
    @Named("idToSequence")
    default CharSequence map(UUID value) {
        return value.toString();
    }
}
