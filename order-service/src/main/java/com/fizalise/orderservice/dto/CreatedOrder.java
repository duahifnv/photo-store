package com.fizalise.orderservice.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record CreatedOrder(UUID orderId, Timestamp orderTimestamp) {
}
