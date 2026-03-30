package com.bootcamp.blog.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(columnDefinition = "text", nullable = false)
  private String content;

  @Column(nullable = false)
  private String authorEmail;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @Builder.Default
  private PostStatus status = PostStatus.DRAFT;

  @CreationTimestamp
  private LocalDateTime createdAt;

  private LocalDateTime publishedAt;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<Comment> comments = new ArrayList<>();

  public void publish() {
    if (this.status != PostStatus.DRAFT) {
      throw new IllegalStateException(
          "Only DRAFT posts can be published. Current status: " + this.status);
    }
    this.status = PostStatus.PUBLISHED;
    this.publishedAt = LocalDateTime.now();
  }
}
