package com.bootcamp.elibrary.service;

import com.bootcamp.elibrary.domain.Loan;
import com.bootcamp.elibrary.domain.LoanStatus;
import com.bootcamp.elibrary.dto.Dtos.*;
import com.bootcamp.elibrary.exception.BookNotFoundException;
import com.bootcamp.elibrary.exception.LoanNotFoundException;
import com.bootcamp.elibrary.repository.BookRepository;
import com.bootcamp.elibrary.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * LoanService — COMPLETE IMPLEMENTATION (no TODOs).
 * Business rules:
 * - A user can have at most 3 active loans simultaneously
 * - A book must have available copies to be checked out
 * - A loan period is 1–30 days
 * - Only the loanee can return a book
 * Students will write tests for this service in LoanServiceTest.java.
 */
@Service
@Transactional(readOnly = true)
public class LoanService {

  private static final int MAX_ACTIVE_LOANS = 3;

  private final LoanRepository loanRepository;
  private final BookRepository bookRepository;

  public LoanService(LoanRepository loanRepository, BookRepository bookRepository) {
    this.loanRepository = loanRepository;
    this.bookRepository = bookRepository;
  }

  public List<LoanResponse> findByUsername(String username) {
    return loanRepository.findByUsername(username).stream()
        .map(this::toResponse)
        .toList();
  }

  public List<LoanResponse> findOverdue() {
    return loanRepository
        .findByDueDateBeforeAndStatus(LocalDate.now(), LoanStatus.ACTIVE)
        .stream()
        .map(this::toResponse)
        .toList();
  }

  @Transactional
  public LoanResponse createLoan(String username, LoanCreateRequest request) {
    // Rule 1: max 3 active loans per user
    long active = loanRepository.countByUsernameAndStatus(username, LoanStatus.ACTIVE);
    if (active >= MAX_ACTIVE_LOANS) {
      throw new IllegalStateException(
          "User " + username + " has reached the maximum of " + MAX_ACTIVE_LOANS + " active loans");
    }

    // Rule 2: book must exist and have available copies
    var book = bookRepository.findById(request.bookId())
        .orElseThrow(() -> new BookNotFoundException(request.bookId()));
    book.checkOut(); // throws IllegalStateException if no copies available
    bookRepository.save(book);

    var loan = new Loan(username, book, request.days());
    return toResponse(loanRepository.save(loan));
  }

  @Transactional
  public LoanResponse returnBook(String username, Long loanId) {
    var loan = loanRepository.findByIdAndUsername(loanId, username)
        .orElseThrow(() -> new LoanNotFoundException(loanId));
    loan.returnBook(); // throws if not ACTIVE
    return toResponse(loanRepository.save(loan));
  }

  // --- Mapper ---
  private LoanResponse toResponse(Loan loan) {
    return new LoanResponse(
        loan.getId(),
        loan.getUsername(),
        loan.getBook().getId(),
        loan.getBook().getTitle(),
        loan.getStatus(),
        loan.getLoanDate(),
        loan.getDueDate(),
        loan.getReturnedAt(),
        loan.isOverdue());
  }
}
