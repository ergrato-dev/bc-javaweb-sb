package com.bootcamp.dto;

import com.bootcamp.domain.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderSummaryResponse(
    Long id,
    String customerName,
    OrderStatus status,
    BigDecimal total,
    LocalDateTime createdAt
) {}
