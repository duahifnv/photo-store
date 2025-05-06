package com.fizalise.orderservice.service;

import com.fizalise.orderservice.client.AuthClient;
import com.fizalise.orderservice.client.InventoryClient;
import com.fizalise.orderservice.client.dto.UserInfo;
import com.fizalise.orderservice.dto.InventoryUpdate;
import com.fizalise.orderservice.dto.OrderRequest;
import com.fizalise.orderservice.dto.OrderResponse;
import com.fizalise.orderservice.entity.Order;
import com.fizalise.orderservice.entity.OrderItem;
import com.fizalise.orderservice.event.OrderPlacedEvent;
import com.fizalise.orderservice.exception.ResourceNotFoundException;
import com.fizalise.orderservice.mapper.OrderMapper;
import com.fizalise.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
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
    private final AuthClient authClient;
    private final OrderMapper orderMapper;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @Value("${topics.order-notification}")
    private String orderNotificationTopic;

    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll(
                        Sort.by(Sort.Direction.DESC, "orderTimestamp")
                ).stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    public List<OrderResponse> findAllOrders(String username) {
        UserInfo userInfo = authClient.getUserInfo(username);
        return orderRepository.findAllByCustomerEmail(userInfo.email()).stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    public OrderResponse findOrderById(UUID id) {
        return orderRepository.findById(id)
                .map(orderMapper::toResponse)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest, String username) {
        UserInfo userInfo = authClient.getUserInfo(username);
        Order order = orderMapper.toOrder(orderRequest, userInfo);

        List<OrderItem> items = orderRequest.items().stream()
                .map(itemRequest -> {
                    OrderItem item = orderMapper.toItem(itemRequest);
                    item.setOrder(order);
                    return item;
                })
                .toList();

        order.setItems(items);

//        checkInventory(orderRequest.items());
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }
    private void checkInventory(List<OrderRequest.OrderItemRequest> items) {
        items.forEach(item ->
                inventoryClient.updateInventory(
                        InventoryUpdate.builder()
                                .updateType(InventoryUpdate.UpdateType.EXPENSE)
                                .skuCode(item.skuCode())
                                .quantity(item.quantity())
                                .build()
                )
        );
    }

    public void sendOrderEvent(String topicId, Order order) {
        OrderPlacedEvent orderPlacedEvent = orderMapper.toEvent(order);
        log.info("Отправка сообщения о созданном заказе в топик {}...", topicId);
        kafkaTemplate.send(topicId, orderPlacedEvent);
        log.info("Сообщение успешно отправлено в топик {}", topicId);
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