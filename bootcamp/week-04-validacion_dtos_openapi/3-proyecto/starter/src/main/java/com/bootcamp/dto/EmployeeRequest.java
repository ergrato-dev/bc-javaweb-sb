package com.bootcamp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

/**
 * Request DTO for creating or updating an employee.
 * All fields are validated with Jakarta Bean Validation constraints.
 */
@Schema(description = "Request body for employee creation or update")
public record EmployeeRequest(

    @Schema(description = "Full name", example = "Alice Johnson") @NotBlank(message = "Name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name,

    @Schema(description = "Work email address", example = "alice.johnson@company.com") @NotBlank(message = "Email is required") @Email(message = "Must be a valid email address") String email,

    @Schema(description = "Age in years", example = "30", minimum = "18", maximum = "65") @Min(value = 18, message = "Age must be at least 18") @Max(value = 65, message = "Age must be at most 65") int age,

    @Schema(description = "Annual salary in USD", example = "75000.00", minimum = "0") @Positive(message = "Salary must be a positive number") double salary,

    @Schema(description = "Department name", example = "Engineering") @NotBlank(message = "Department is required") String department,

    @Schema(description = "Job position title", example = "Senior Software Engineer") @NotBlank(message = "Position is required") String position) {
}
