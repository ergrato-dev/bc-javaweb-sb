package com.bootcamp.service;

import com.bootcamp.dto.ProductRequest;
import com.bootcamp.dto.ProductResponse;
import com.bootcamp.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;

/**
 * Business logic for product catalog management.
 * Uses in-memory storage (will be replaced with JPA in week 05).
 */
@Service
public class ProductService {

  private final AtomicLong idCounter = new AtomicLong(1);
  private final List<ProductResponse> store = new ArrayList<>(List.of(
      new ProductResponse(idCounter.getAndIncrement(), "Laptop", "Electronics", 999.00, 10, true),
      new ProductResponse(idCounter.getAndIncrement(), "Phone", "Electronics", 599.00, 5, true),
      new ProductResponse(idCounter.getAndIncrement(), "Desk", "Furniture", 299.00, 3, true),
      new ProductResponse(idCounter.getAndIncrement(), "Chair", "Furniture", 199.00, 0, false),
      new ProductResponse(idCounter.getAndIncrement(), "Keyboard", "Electronics", 79.99, 8, true)));

  /**
   * Returns all products, optionally filtered by category.
   *
   * TODO:
   * 1. If category is not null and not blank, filter by category
   * (case-insensitive)
   * 2. Otherwise, return all products
   */
  public List<ProductResponse> findAll(String category) {
    // TODO: Implement with optional category filter
    return List.copyOf(store);
  }

  /**
   * Finds a product by its ID.
   *
   * TODO:
   * 1. Stream over store
   * 2. Filter by id
   * 3. Return findFirst().orElseThrow(ProductNotFoundException)
   */
  public ProductResponse findById(Long id) {
    // TODO: Implement and throw ProductNotFoundException when not found
    return null;
  }

  /**
   * Creates a new product and adds it to the store.
   *
   * TODO:
   * 1. Validate: name must not be blank
   * 2. Validate: price must be > 0
   * 3. Create ProductResponse with auto-incremented ID
   * 4. inStock = stock > 0
   * 5. Add to store and return created product
   */
  public ProductResponse create(ProductRequest request) {
    // TODO: Implement with validation
    return null;
  }

  /**
   * Updates an existing product.
   *
   * TODO:
   * 1. Find by ID (throw ProductNotFoundException if not found)
   * 2. Create updated ProductResponse with the same ID
   * 3. Replace in store
   * 4. Return updated product
   */
  public ProductResponse update(Long id, ProductRequest request) {
    // TODO: Implement update
    return null;
  }

  /**
   * Deletes a product by ID.
   *
   * TODO:
   * 1. Find by ID (throw ProductNotFoundException if not found)
   * 2. Remove from store
   */
  public void delete(Long id) {
    // TODO: Implement delete
  }
}
