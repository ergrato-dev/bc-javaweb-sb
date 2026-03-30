package com.bootcamp.elibrary.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String username;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private LoanStatus status = LoanStatus.ACTIVE;

  @Column(nullable = false)
  private LocalDate loanDate;

  @Column(nullable = false)
  private LocalDate dueDate;

  @Column
  private LocalDate returnedAt;

  protected Loan() {
  }

  public Loan(String username, Book book, int days) {
    this.username = username;
    this.book = book;
    this.loanDate = LocalDate.now();
    this.dueDate = LocalDate.now().plusDays(days);
    this.status = LoanStatus.ACTIVE;
  }

  // Getters
  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public Book getBook() {
    return book;
  }

  public LoanStatus getStatus() {
    return status;
  }

  public LocalDate getLoanDate() {
    return loanDate;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public LocalDate getReturnedAt() {
    return returnedAt;
  }

  public boolean isOverdue() {
    return status == LoanStatus.ACTIVE && LocalDate.now().isAfter(dueDate);
  }

  public void returnBook() {
    if (status != LoanStatus.ACTIVE)
      throw new IllegalStateException("Loan is not active");
    this.status = LoanStatus.RETURNED;
    this.returnedAt = LocalDate.now();
    this.book.returnCopy();
  }
}
