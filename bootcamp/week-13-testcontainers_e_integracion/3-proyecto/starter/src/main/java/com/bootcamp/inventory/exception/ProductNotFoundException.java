package com.bootcamp.inventory.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) { super("Product not found: " + id); }
    public ProductNotFoundException(String sku) { super("Product not found: " + sku); }
}
