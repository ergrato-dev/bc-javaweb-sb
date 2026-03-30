package com.bootcamp.notifications.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(columnDefinition = "text")
  private String content;

  @Column(nullable = false)
  private String authorEmail;

  @Column(nullable = false)
  private String category;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ArticleStatus status;

  @CreationTimestamp
  private LocalDateTime createdAt;

  private LocalDateTime publishedAt;

  public void publish() {
    this.status = ArticleStatus.PUBLISHED;
    this.publishedAt = LocalDateTime.now();
  }

  public void archive() {
    this.status = ArticleStatus.ARCHIVED;
  }
}
