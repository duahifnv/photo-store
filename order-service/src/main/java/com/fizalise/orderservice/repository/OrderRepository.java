package com.fizalise.orderservice.repository;

import com.fizalise.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    boolean existsByCustomerEmail(String customerEmail);
    List<Order> findAllByCustomerEmail(String customerEmail);
}
