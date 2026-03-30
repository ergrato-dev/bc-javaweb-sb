package com.bootcamp.dto;

import java.time.LocalDateTime;

public record PostSummaryResponse(Long id, String title, boolean published, String authorName,
    LocalDateTime createdAt) {
}
