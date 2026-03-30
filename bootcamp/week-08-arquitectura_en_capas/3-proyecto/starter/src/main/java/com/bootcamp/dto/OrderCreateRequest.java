package com.bootcamp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record OrderCreateRequest(
    @NotNull Long customerId,
    @NotEmpty List<OrderItemRequest> items,
    String notes) {
}
