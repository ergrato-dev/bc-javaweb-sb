package com.bootcamp.domain;

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
  @Column
  private String description;
  @Column(nullable = false)
  private BigDecimal price;
  @Column(nullable = false)
  private int stock;
  @Column
  private String category;
  @Column(nullable = false)
  private boolean active = true;
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  protected Product() {
  }

  public Product(String name, String description, BigDecimal price, int stock, String category) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.category = category;
    this.createdAt = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public int getStock() {
    return stock;
  }

  public String getCategory() {
    return category;
  }

  public boolean isActive() {
    return active;
  }

  public void decrementStock(int qty) {
    if (this.stock < qty)
      throw new IllegalStateException("Insufficient stock for product: " + id);
    this.stock -= qty;
  }
}
