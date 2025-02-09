package com.fizalise.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fizalise.orderservice.client.InventoryClient;
import com.fizalise.orderservice.dto.CreatedOrder;
import com.fizalise.orderservice.dto.InventoryUpdate;
import com.fizalise.orderservice.dto.OrderRequest;
import com.fizalise.orderservice.entity.Order;
import com.fizalise.orderservice.mapper.OrderMapper;
import com.fizalise.orderservice.repository.OrderRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public record OrderService(OrderRepository orderRepository, InventoryClient inventoryClient,
                           OrderMapper orderMapper) {
    public List<Order> findAllOrders() {
        return orderRepository.findAll(
                Sort.by(Sort.Direction.DESC, "orderTimestamp")
        );
    }
    public CreatedOrder createOrder(OrderRequest orderRequest) {
        try {
            inventoryClient.updateInventory(
                    InventoryUpdate.builder()
                            .updateType(InventoryUpdate.UpdateType.EXPENSE)
                            .skuCode(orderRequest.skuCode())
                            .quantity(orderRequest.quantity()).build()
            );
        } catch (HttpClientErrorException e) {
            try {
                String reason = getReasonFromSerializedMessage(e.getMessage());
                throw new ResponseStatusException(e.getStatusCode(), reason);
            } catch (JsonProcessingException je) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Технические неполадки");
            }
        }
        Order order = orderMapper.toOrder(orderRequest, orderRequest.userDetails().email());
        orderRepository.save(order);
        return orderMapper.toCreatedOrder(order);
    }
    private String getReasonFromSerializedMessage(String message)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ErrorResponse errorResponse = objectMapper.readValue(message, ErrorResponse.class);
        return errorResponse.getDetailMessageCode();
    }
}
