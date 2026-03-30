package com.bootcamp.elibrary.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(unique = true, nullable = false, length = 13)
  private String isbn;

  @Column(nullable = false)
  private String author;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private BookStatus status = BookStatus.AVAILABLE;

  @Column(nullable = false)
  private int totalCopies;

  @Column(nullable = false)
  private int availableCopies;

  // Required by JPA
  protected Book() {
  }

  public Book(Long id, String title, String isbn, String author, BigDecimal price, int copies) {
    this.id = id;
    this.title = title;
    this.isbn = isbn;
    this.author = author;
    this.price = price;
    this.totalCopies = copies;
    this.availableCopies = copies;
    this.status = copies > 0 ? BookStatus.AVAILABLE : BookStatus.UNAVAILABLE;
  }

  // Getters
  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getAuthor() {
    return author;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BookStatus getStatus() {
    return status;
  }

  public int getTotalCopies() {
    return totalCopies;
  }

  public int getAvailableCopies() {
    return availableCopies;
  }

  // Setters
  public void setTitle(String title) {
    this.title = title;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public void checkOut() {
    if (availableCopies < 1)
      throw new IllegalStateException("No copies available");
    availableCopies--;
    if (availableCopies == 0)
      status = BookStatus.UNAVAILABLE;
  }

  public void returnCopy() {
    if (availableCopies >= totalCopies)
      throw new IllegalStateException("All copies already returned");
    availableCopies++;
    status = BookStatus.AVAILABLE;
  }
}
