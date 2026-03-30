package com.bootcamp.dto;

import com.bootcamp.domain.PaymentStatus;
import com.bootcamp.domain.Role;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
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

public record AuthResponse(
        String accessToken,
        long expiresIn
) {}

public record UserResponse(
        Long id,
        String username,
        Role role,
        LocalDateTime createdAt
) {}

// ─── Payment DTOs ─────────────────────────────────────────────

public record PaymentCreateRequest(
        @NotNull @DecimalMin("0.01") BigDecimal amount,
        @NotBlank @Size(min = 3, max = 3) String currency,
        @NotBlank String recipientAccount,
        String description
) {}

public record PaymentResponse(
        Long id,
        BigDecimal amount,
        String currency,
        String recipientAccount,
        PaymentStatus status,
        String ownerUsername,
        String description,
        LocalDateTime createdAt
) {}
