package com.bootcamp.dto;

import com.bootcamp.domain.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
    Long id,
    Long customerId, String customerName,
    OrderStatus status,
    BigDecimal total,
    String notes,
    List<OrderItemResponse> items,
    LocalDateTime createdAt
) {}
