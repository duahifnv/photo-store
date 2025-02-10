package com.fizalise.orderservice.service;

import com.fizalise.orderservice.client.InventoryClient;
import com.fizalise.orderservice.dto.CreatedOrder;
import com.fizalise.orderservice.dto.InventoryUpdate;
import com.fizalise.orderservice.dto.OrderRequest;
import com.fizalise.orderservice.entity.Order;
import com.fizalise.orderservice.exception.ResourceNotFoundException;
import com.fizalise.orderservice.mapper.OrderMapper;
import com.fizalise.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final OrderMapper orderMapper;
    public List<Order> findAllOrders() {
        return orderRepository.findAll(
                Sort.by(Sort.Direction.DESC, "orderTimestamp")
        );
    }
    @Transactional
    public CreatedOrder createOrder(OrderRequest orderRequest) {
        inventoryClient.updateInventory(
                InventoryUpdate.builder()
                        .updateType(InventoryUpdate.UpdateType.EXPENSE)
                        .skuCode(orderRequest.skuCode())
                        .quantity(orderRequest.quantity()).build()
        );
        Order order = orderMapper.toOrder(orderRequest, orderRequest.userDetails().email());
        log.info("Создан новый заказ: {}", order.getOrderId());
        orderRepository.save(order);
        log.info("Заказ {} сохранен в базу данных", order.getOrderId());
        return orderMapper.toCreatedOrder(order);
    }
    @Transactional
    public void deleteOrderById(UUID id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
        orderRepository.deleteById(id);
        log.info("Заказ {} удален из базы данных", id);
    }
}
