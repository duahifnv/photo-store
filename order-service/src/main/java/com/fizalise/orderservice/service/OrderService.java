package com.fizalise.orderservice.service;

import com.fizalise.orderservice.client.AuthClient;
import com.fizalise.orderservice.client.InventoryClient;
import com.fizalise.orderservice.client.dto.UserInfo;
import com.fizalise.orderservice.dto.InventoryUpdate;
import com.fizalise.orderservice.dto.OrderRequest;
import com.fizalise.orderservice.entity.Order;
import com.fizalise.orderservice.event.OrderPlacedEvent;
import com.fizalise.orderservice.exception.BadRequestException;
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
import org.springframework.web.client.HttpClientErrorException;

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
    public List<Order> findAllOrders() {
        return orderRepository.findAll(
                Sort.by(Sort.Direction.DESC, "orderTimestamp")
        );
    }
    public List<Order> findAllOrders(String username) {
        UserInfo userInfo = authClient.getUserInfo(username);
        return orderRepository.findAllByCustomerEmail(userInfo.email());
    }
    public Order findOrderById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }
    @Transactional
    public Order createOrder(OrderRequest orderRequest, String username) {
        inventoryClient.updateInventory(
                InventoryUpdate.builder()
                        .updateType(InventoryUpdate.UpdateType.EXPENSE)
                        .skuCode(orderRequest.skuCode())
                        .quantity(orderRequest.quantity()).build()
        );
        Order order = orderMapper.toOrder(orderRequest, authClient.getUserInfo(username));
        orderRepository.save(order);
        log.info("Заказ {} сохранен в базу данных", order);
        sendOrderEvent(orderNotificationTopic, order);
        return order;
    }
    public void sendOrderEvent(String topicId, Order order) {
        OrderPlacedEvent orderPlacedEvent = orderMapper.toEvent(order);
        log.info("Отправка сообщения о созданном заказе в топик %s...".formatted(topicId));
        kafkaTemplate.send(topicId, orderPlacedEvent);
        log.info("Сообщение успешно отправлено в топик %s...".formatted(topicId));
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
