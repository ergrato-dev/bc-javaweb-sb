package com.bootcamp.dto;

import com.bootcamp.domain.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record OrderUpdateRequest(
    @NotNull OrderStatus status,
    String notes) {
}
