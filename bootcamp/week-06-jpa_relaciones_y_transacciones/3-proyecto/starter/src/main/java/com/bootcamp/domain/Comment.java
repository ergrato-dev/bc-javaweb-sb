package com.bootcamp.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 1000)
  private String content;

  @Column(name = "author_name", nullable = false)
  private String authorName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  protected Comment() {
  }

  public Comment(String content, String authorName) {
    this.content = content;
    this.authorName = authorName;
  }

  public Long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public String getAuthorName() {
    return authorName;
  }

  public Post getPost() {
    return post;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
