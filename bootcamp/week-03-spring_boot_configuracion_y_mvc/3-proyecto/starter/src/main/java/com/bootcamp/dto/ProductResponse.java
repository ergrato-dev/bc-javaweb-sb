package com.bootcamp.dto;

/**
 * Response DTO for product data sent to clients.
 * Never expose internal domain/entity objects directly via API.
 */
public record ProductResponse(
        Long id,
        String name,
        String category,
        double price,
        int stock,
        boolean inStock
) {}
