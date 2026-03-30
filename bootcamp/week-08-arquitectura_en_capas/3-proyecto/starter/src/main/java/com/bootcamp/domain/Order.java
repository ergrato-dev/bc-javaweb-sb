package com.bootcamp.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private OrderStatus status = OrderStatus.PENDING;
  @Column(nullable = false)
  private BigDecimal total = BigDecimal.ZERO;
  @Column
  private String notes;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> items = new ArrayList<>();

  protected Order() {
  }

  public Order(Customer customer, String notes) {
    this.customer = customer;
    this.notes = notes;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public void addItem(OrderItem item) {
    item.setOrder(this);
    items.add(item);
    recalculateTotal();
  }

  public void recalculateTotal() {
    this.total = items.stream()
        .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    this.updatedAt = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public String getNotes() {
    return notes;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
    this.updatedAt = LocalDateTime.now();
  }
}
