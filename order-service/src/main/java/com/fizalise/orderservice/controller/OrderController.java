package com.fizalise.orderservice.controller;

import com.fizalise.orderservice.dto.CreatedOrder;
import com.fizalise.orderservice.dto.OrderRequest;
import com.fizalise.orderservice.entity.Order;
import com.fizalise.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public record OrderController(OrderService orderService) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        return orderService.findAllOrders();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedOrder createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrderById(@PathVariable UUID id) {
        orderService.deleteOrderById(id);
    }
}
