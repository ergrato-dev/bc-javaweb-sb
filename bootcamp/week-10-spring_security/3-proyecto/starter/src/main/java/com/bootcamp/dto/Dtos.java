package com.bootcamp.dto;

import com.bootcamp.domain.Role;
import com.bootcamp.domain.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

// ─── Auth DTOs ───────────────────────────────────────────────

public record RegisterRequest(
        @NotBlank String username,
        @NotBlank @Size(min = 6) String password,
        Role role
) {}

public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password
) {}

public record UserResponse(
        Long id,
        String username,
        Role role,
        boolean active,
        LocalDateTime createdAt
) {}

// ─── Task DTOs ───────────────────────────────────────────────

public record TaskCreateRequest(
        @NotBlank String title,
        String description
) {}

public record TaskUpdateRequest(
        String title,
        String description,
        TaskStatus status
) {}

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        String ownerUsername,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
