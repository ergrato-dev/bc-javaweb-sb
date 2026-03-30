package com.bootcamp;

// ============================================================
// ServiceLayerApp.java — Práctica 02: Service Layer con reglas
// Descomenta cada PASO en orden para ver la implementación
// ============================================================

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class ServiceLayerApp {
  public static void main(String[] args) {
    SpringApplication.run(ServiceLayerApp.class, args);
  }
}

// ============================================================
// PASO 1: Dominio — 3 entidades
// ============================================================
// Descomenta las siguientes líneas:
//
// @Entity @Table(name = "books")
// class Book {
// @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
// @Column(nullable = false) String title;
// @Column(nullable = false) String isbn;
// @Column(nullable = false) boolean available = true;
// protected Book() {}
// Book(String title, String isbn) { this.title = title; this.isbn = isbn; }
// public Long getId() { return id; }
// public String getTitle() { return title; }
// public boolean isAvailable() { return available; }
// public void borrow() { this.available = false; }
// public void returnBook() { this.available = true; }
// }
//
// @Entity @Table(name = "members")
// class Member {
// @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
// @Column(nullable = false) String name;
// @Column(nullable = false, unique = true) String email;
// protected Member() {}
// Member(String name, String email) { this.name = name; this.email = email; }
// public Long getId() { return id; }
// public String getName() { return name; }
// }
//
// enum LoanStatus { ACTIVE, RETURNED, OVERDUE }
//
// @Entity @Table(name = "loans")
// class Loan {
// @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
// @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "book_id") Book book;
// @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "member_id") Member
// member;
// @Column(name = "borrowed_at") LocalDateTime borrowedAt;
// @Column(name = "due_at") LocalDateTime dueAt;
// @Column(name = "returned_at") LocalDateTime returnedAt;
// @Enumerated(EnumType.STRING) LoanStatus status = LoanStatus.ACTIVE;
// protected Loan() {}
// Loan(Book book, Member member) {
// this.book = book; this.member = member;
// this.borrowedAt = LocalDateTime.now();
// this.dueAt = borrowedAt.plusDays(14);
// }
// public Long getId() { return id; }
// public Book getBook() { return book; }
// public Member getMember() { return member; }
// public LoanStatus getStatus() { return status; }
// public LocalDateTime getDueAt() { return dueAt; }
// public void returnLoan() {
// this.returnedAt = LocalDateTime.now();
// this.status = returnedAt.isAfter(dueAt) ? LoanStatus.OVERDUE :
// LoanStatus.RETURNED;
// }
// }

// ============================================================
// PASO 2: Repositories
// ============================================================
// Descomenta las siguientes líneas:
//
// interface BookRepository extends JpaRepository<Book, Long> {}
// interface MemberRepository extends JpaRepository<Member, Long> {}
// interface LoanRepository extends JpaRepository<Loan, Long> {
// long countByMember_IdAndStatus(Long memberId, LoanStatus status);
// boolean existsByBook_IdAndStatus(Long bookId, LoanStatus status);
// List<Loan> findByMember_Id(Long memberId);
// }

// ============================================================
// PASO 3: LoanService — Reglas de negocio
// ============================================================
// MAX_LOANS = 3 — lanzar excepción si se excede
// Verificar disponibilidad del libro antes de prestar
// Descomenta las siguientes líneas:
//
// @Service
// @Transactional(readOnly = true)
// class LoanService {
//
// private static final int MAX_ACTIVE_LOANS = 3;
//
// private final LoanRepository loanRepository;
// private final BookRepository bookRepository;
// private final MemberRepository memberRepository;
//
// LoanService(LoanRepository l, BookRepository b, MemberRepository m) {
// this.loanRepository = l; this.bookRepository = b; this.memberRepository = m;
// }
//
// @Transactional
// public LoanResponse borrow(Long memberId, Long bookId) {
// var member = memberRepository.findById(memberId)
// .orElseThrow(() -> new ResourceNotFoundException("Member", memberId));
//
// var book = bookRepository.findById(bookId)
// .orElseThrow(() -> new ResourceNotFoundException("Book", bookId));
//
// // Regla 1: Máximo 3 préstamos activos
// long active = loanRepository.countByMember_IdAndStatus(memberId,
// LoanStatus.ACTIVE);
// if (active >= MAX_ACTIVE_LOANS) {
// throw new LoanLimitExceededException(memberId, MAX_ACTIVE_LOANS);
// }
//
// // Regla 2: Libro debe estar disponible
// if (!book.isAvailable()) {
// throw new BookNotAvailableException(bookId);
// }
//
// book.borrow();
// var loan = loanRepository.save(new Loan(book, member));
// return toResponse(loan);
// }
//
// @Transactional
// public LoanResponse returnLoan(Long loanId) {
// var loan = loanRepository.findById(loanId)
// .orElseThrow(() -> new ResourceNotFoundException("Loan", loanId));
//
// if (loan.getStatus() != LoanStatus.ACTIVE) {
// throw new IllegalStateException("Loan is not active");
// }
//
// loan.returnLoan(); // calcula RETURNED vs OVERDUE
// loan.getBook().returnBook();
//
// return toResponse(loan);
// }
//
// private LoanResponse toResponse(Loan l) {
// return new LoanResponse(l.getId(), l.getBook().getTitle(),
// l.getMember().getName(), l.getDueAt(), l.getStatus());
// }
// }
//
// class ResourceNotFoundException extends RuntimeException {
// ResourceNotFoundException(String type, Long id) {
// super(type + " not found with id: " + id);
// }
// }
// class LoanLimitExceededException extends RuntimeException {
// LoanLimitExceededException(Long memberId, int limit) {
// super("Member " + memberId + " has reached the limit of " + limit + " active
// loans");
// }
// }
// class BookNotAvailableException extends RuntimeException {
// BookNotAvailableException(Long id) { super("Book " + id + " is not
// available"); }
// }

// ============================================================
// PASO 4: Controller limpio
// ============================================================
// Descomenta las siguientes líneas:
//
// @RestController
// @RequestMapping("/api/loans")
// class LoanController {
//
// private final LoanService loanService;
//
// LoanController(LoanService loanService) { this.loanService = loanService; }
//
// @PostMapping
// public ResponseEntity<LoanResponse> borrow(@Valid @RequestBody BorrowRequest
// request) {
// return ResponseEntity.status(HttpStatus.CREATED)
// .body(loanService.borrow(request.memberId(), request.bookId()));
// }
//
// @PatchMapping("/{id}/return")
// public ResponseEntity<LoanResponse> returnLoan(@PathVariable Long id) {
// return ResponseEntity.ok(loanService.returnLoan(id));
// }
//
// @ExceptionHandler(LoanLimitExceededException.class)
// ResponseEntity<String> handleLimit(LoanLimitExceededException ex) {
// return ResponseEntity.unprocessableEntity().body(ex.getMessage());
// }
//
// @ExceptionHandler(BookNotAvailableException.class)
// ResponseEntity<String> handleUnavailable(BookNotAvailableException ex) {
// return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
// }
// }
//
// record BorrowRequest(@NotNull Long memberId, @NotNull Long bookId) {}
// record LoanResponse(Long id, String bookTitle, String memberName,
// LocalDateTime dueAt, LoanStatus status) {}
