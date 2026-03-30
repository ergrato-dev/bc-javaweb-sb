package com.bootcamp.blog.dto;

import com.bootcamp.blog.domain.PostStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public class Dtos {

  public record PostCreateRequest(
      @NotBlank String title,
      @NotBlank String content,
      @NotBlank @Email String authorEmail) {
  }

  public record CommentCreateRequest(
      @NotBlank String content,
      @NotBlank @Email String authorEmail) {
  }

  public record CommentResponse(
      Long id,
      String content,
      String authorEmail,
      LocalDateTime createdAt) {
  }

  public record PostResponse(
      Long id,
      String title,
      String content,
      String authorEmail,
      PostStatus status,
      LocalDateTime createdAt,
      LocalDateTime publishedAt,
      List<CommentResponse> comments) {
  }

  public record PostSummaryResponse(
      Long id,
      String title,
      String authorEmail,
      PostStatus status,
      LocalDateTime createdAt,
      int commentCount) {
  }
}
