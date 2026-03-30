package com.bootcamp.notifications.dto;

import com.bootcamp.notifications.domain.ArticleStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class Dtos {

    public record ArticleCreateRequest(
        @NotBlank String title,
        @NotBlank String content,
        @NotBlank @Email String authorEmail,
        @NotBlank String category
    ) {}

    public record ArticleUpdateRequest(
        @NotBlank String title,
        @NotBlank String content,
        @NotBlank String category
    ) {}

    public record ArticleResponse(
        Long id,
        String title,
        String content,
        String authorEmail,
        String category,
        ArticleStatus status,
        LocalDateTime createdAt,
        LocalDateTime publishedAt
    ) {}
}
