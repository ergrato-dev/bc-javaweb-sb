package com.bootcamp.elibrary.dto;

import com.bootcamp.elibrary.domain.BookStatus;
import com.bootcamp.elibrary.domain.LoanStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * All DTOs for the E-Library API.
 * Using records for immutable, concise data transfer objects (Java 16+).
 */
public class Dtos {

    // -------------------------
    // Book DTOs
    // -------------------------

    public record BookCreateRequest(
        @NotBlank(message = "Title is required")
        @Size(max = 255)
        String title,

        @NotBlank(message = "ISBN is required")
        @Pattern(regexp = "\\d{13}", message = "ISBN must be 13 digits")
        String isbn,

        @NotBlank(message = "Author is required")
        String author,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be positive")
        BigDecimal price,

        @Min(value = 1, message = "Must have at least 1 copy")
        int copies
    ) {}

    public record BookUpdateRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal price
    ) {}

    public record BookResponse(
        Long id,
        String title,
        String isbn,
        String author,
        BigDecimal price,
        BookStatus status,
        int availableCopies,
        int totalCopies
    ) {}

    // -------------------------
    // Loan DTOs
    // -------------------------

    public record LoanCreateRequest(
        @NotNull(message = "Book ID is required")
        Long bookId,

        @Min(value = 1, message = "Minimum loan period is 1 day")
        @Max(value = 30, message = "Maximum loan period is 30 days")
        int days
    ) {}

    public record LoanResponse(
        Long id,
        String username,
        Long bookId,
        String bookTitle,
        LoanStatus status,
        LocalDate loanDate,
        LocalDate dueDate,
        LocalDate returnedAt,
        boolean overdue
    ) {}
}
