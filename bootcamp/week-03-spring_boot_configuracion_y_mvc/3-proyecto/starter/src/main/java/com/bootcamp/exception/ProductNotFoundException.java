package com.bootcamp.exception;

/**
 * Thrown when a requested resource does not exist.
 * Mapped to HTTP 404 Not Found by GlobalExceptionHandler.
 */
public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(Long id) {
    super("Product not found with id: " + id);
  }
}
