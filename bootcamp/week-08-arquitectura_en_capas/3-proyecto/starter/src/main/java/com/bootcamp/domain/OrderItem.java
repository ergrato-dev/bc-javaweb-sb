package com.bootcamp.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;
  @Column(nullable = false)
  private int quantity;
  @Column(name = "unit_price", nullable = false)
  private BigDecimal unitPrice;

  protected OrderItem() {
  }

  public OrderItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
    this.unitPrice = product.getPrice();
  }

  public Long getId() {
    return id;
  }

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setOrder(Order order) {
    this.order = order;
  }
}
