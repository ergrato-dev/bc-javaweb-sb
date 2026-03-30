package com.bootcamp.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PostDetailResponse(
    Long id, String title, String content, boolean published,
    String authorName, LocalDateTime createdAt, LocalDateTime updatedAt,
    List<CommentResponse> comments) {
}
