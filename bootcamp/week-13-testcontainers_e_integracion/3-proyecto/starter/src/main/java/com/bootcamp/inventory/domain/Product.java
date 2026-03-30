package com.bootcamp.inventory.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String sku;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private String category;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    protected Product() {}

    public Product(Long id, String name, String sku, BigDecimal price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.createdAt = LocalDateTime.now();
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSku() { return sku; }
    public BigDecimal getPrice() { return price; }
    public int getStock() { return stock; }
    public String getCategory() { return category; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Business methods
    public void updateDetails(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.updatedAt = LocalDateTime.now();
    }

    public void addStock(int quantity) {
        if (quantity < 1) throw new IllegalArgumentException("Quantity must be positive");
        this.stock += quantity;
        this.updatedAt = LocalDateTime.now();
    }

    public void removeStock(int quantity) {
        if (quantity < 1) throw new IllegalArgumentException("Quantity must be positive");
        if (quantity > this.stock) throw new IllegalStateException("Insufficient stock: " + this.stock);
        this.stock -= quantity;
        this.updatedAt = LocalDateTime.now();
    }
}
