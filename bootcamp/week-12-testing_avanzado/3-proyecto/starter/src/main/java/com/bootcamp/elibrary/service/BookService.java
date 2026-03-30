package com.bootcamp.elibrary.service;

import com.bootcamp.elibrary.domain.Book;
import com.bootcamp.elibrary.dto.Dtos.*;
import com.bootcamp.elibrary.exception.BookNotFoundException;
import com.bootcamp.elibrary.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * BookService — COMPLETE IMPLEMENTATION (no TODOs).
 * Students will write tests for this service in BookServiceTest.java.
 */
@Service
@Transactional(readOnly = true)
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Page<BookResponse> findAll(Pageable pageable) {
    return bookRepository.findAll(pageable).map(this::toResponse);
  }

  public BookResponse findById(Long id) {
    return bookRepository.findById(id)
        .map(this::toResponse)
        .orElseThrow(() -> new BookNotFoundException(id));
  }

  public Optional<BookResponse> findByIsbn(String isbn) {
    return bookRepository.findByIsbn(isbn).map(this::toResponse);
  }

  @Transactional
  public BookResponse create(BookCreateRequest request) {
    if (bookRepository.existsByIsbn(request.isbn())) {
      throw new IllegalArgumentException("Book with ISBN " + request.isbn() + " already exists");
    }
    var book = new Book(null, request.title(), request.isbn(),
        request.author(), request.price(), request.copies());
    return toResponse(bookRepository.save(book));
  }

  @Transactional
  public BookResponse update(Long id, BookUpdateRequest request) {
    var book = bookRepository.findById(id)
        .orElseThrow(() -> new BookNotFoundException(id));
    book.setTitle(request.title());
    book.setPrice(request.price());
    return toResponse(bookRepository.save(book));
  }

  @Transactional
  public void delete(Long id) {
    if (!bookRepository.existsById(id))
      throw new BookNotFoundException(id);
    bookRepository.deleteById(id);
  }

  // --- Mapper ---
  private BookResponse toResponse(Book book) {
    return new BookResponse(
        book.getId(),
        book.getTitle(),
        book.getIsbn(),
        book.getAuthor(),
        book.getPrice(),
        book.getStatus(),
        book.getAvailableCopies(),
        book.getTotalCopies());
  }
}
