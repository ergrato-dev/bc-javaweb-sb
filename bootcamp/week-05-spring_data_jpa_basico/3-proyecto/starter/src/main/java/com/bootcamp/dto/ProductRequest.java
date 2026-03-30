package com.bootcamp.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(
    @NotBlank(message = "Name is required")
    String name,

    String description,

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    BigDecimal price,

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    Integer stock,

    @NotBlank(message = "Category is required")
    String category
) {}
