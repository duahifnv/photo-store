package com.fizalise.orderservice.controller;

import com.fizalise.orderservice.dto.OrderRequest;
import com.fizalise.orderservice.dto.OrderResponse;
import com.fizalise.orderservice.entity.Order;
import com.fizalise.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @Operation(summary = "Получить все заказы")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders() {
        return orderService.findAllOrders();
    }
    @Operation(summary = "Получить мои заказы")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user")
    public List<OrderResponse> getAllOrders(Principal principal) {
        return orderService.findAllOrders(principal.getName());
    }
    @Operation(summary = "Получить заказ по его коду")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrderById(@PathVariable UUID id) {
        return orderService.findOrderById(id);
    }
    @Operation(summary = "Оформить новый заказ")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest orderRequest, Principal principal) {
        return orderService.createOrder(orderRequest, principal.getName());
    }
    @Operation(summary = "Удалить заказ по его коду")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrderById(@PathVariable UUID id) {
        orderService.deleteOrderById(id);
    }
}
