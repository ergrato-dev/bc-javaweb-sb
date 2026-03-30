package com.bootcamp.dto;

import java.time.LocalDateTime;

public record CommentResponse(Long id, String content, String authorName, LocalDateTime createdAt) {
}
