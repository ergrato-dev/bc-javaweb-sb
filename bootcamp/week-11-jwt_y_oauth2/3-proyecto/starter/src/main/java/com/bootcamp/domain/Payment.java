package com.bootcamp.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@EntityListeners(AuditingEntityListener.class)
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, precision = 18, scale = 2)
  private BigDecimal amount;

  @Column(nullable = false, length = 3)
  private String currency;

  @Column(nullable = false, length = 100)
  private String recipientAccount;

  @Column(nullable = false, length = 50)
  @Enumerated(EnumType.STRING)
  private PaymentStatus status = PaymentStatus.PENDING;

  @Column(nullable = false)
  private String ownerUsername;

  private String description;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdAt;

  protected Payment() {
  }

  public Payment(BigDecimal amount, String currency, String recipientAccount,
      String ownerUsername, String description) {
    this.amount = amount;
    this.currency = currency;
    this.recipientAccount = recipientAccount;
    this.ownerUsername = ownerUsername;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }

  public String getRecipientAccount() {
    return recipientAccount;
  }

  public PaymentStatus getStatus() {
    return status;
  }

  public String getOwnerUsername() {
    return ownerUsername;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setStatus(PaymentStatus status) {
    this.status = status;
  }
}
