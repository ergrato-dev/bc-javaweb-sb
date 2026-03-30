package com.bootcamp.inventory.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Dtos {

  public record ProductCreateRequest(
      @NotBlank String name,
      @NotBlank @Pattern(regexp = "[A-Z0-9-]+", message = "SKU must be uppercase alphanumeric") String sku,
      @NotNull @DecimalMin("0.01") BigDecimal price,
      @Min(0) int stock,
      @NotBlank String category) {
  }

  public record ProductUpdateRequest(
      @NotBlank String name,
      @NotNull @DecimalMin("0.01") BigDecimal price) {
  }

  public record StockAdjustRequest(
      @NotNull @Min(1) int quantity) {
  }

  public record ProductResponse(
      Long id,
      String name,
      String sku,
      BigDecimal price,
      int stock,
      String category,
      LocalDateTime createdAt) {
  }
}
