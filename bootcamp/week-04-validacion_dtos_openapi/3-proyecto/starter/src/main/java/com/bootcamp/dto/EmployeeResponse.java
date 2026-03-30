package com.bootcamp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response DTO for employee data.
 * Only exposes non-sensitive fields.
 */
@Schema(description = "Employee data returned by the API")
public record EmployeeResponse(
    @Schema(description = "Unique identifier", example = "1")
    Long id,

    @Schema(description = "Full name", example = "Alice Johnson")
    String name,

    @Schema(description = "Work email", example = "alice.johnson@company.com")
    String email,

    @Schema(description = "Age", example = "30")
    int age,

    @Schema(description = "Department", example = "Engineering")
    String department,

    @Schema(description = "Job position", example = "Senior Software Engineer")
    String position
) {}
