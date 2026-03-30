package com.bootcamp.dto;

/**
 * Request DTO for creating or updating a product.
 */
public record ProductRequest(
    String name,
    String category,
    double price,
    int stock) {
}
