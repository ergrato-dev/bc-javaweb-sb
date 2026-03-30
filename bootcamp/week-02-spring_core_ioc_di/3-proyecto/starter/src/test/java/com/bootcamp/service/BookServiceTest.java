package com.bootcamp.service;

import com.bootcamp.model.Book;
import com.bootcamp.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookService (Proyecto Semana 02).
 * Uses Mockito to mock BookRepository.
 */
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

  @Mock
  private BookRepository bookRepository;

  @InjectMocks
  private BookService bookService;

  private List<Book> sampleBooks;

  @BeforeEach
  void setUp() {
    sampleBooks = List.of(
        new Book("ISBN-001", "Spring in Action", "Craig Walls", "Java", true),
        new Book("ISBN-002", "Clean Code", "Robert Martin", "Programming", true),
        new Book("ISBN-003", "Effective Java", "Joshua Bloch", "Java", false),
        new Book("ISBN-004", "Design Patterns", "Gang of Four", "Architecture", true));
  }

  @Test
  @DisplayName("getAllBooksSortedByTitle returns books in alphabetical order")
  void getAllBooksSortedByTitle_returnsSorted() {
    when(bookRepository.findAll()).thenReturn(sampleBooks);

    var result = bookService.getAllBooksSortedByTitle();

    assertEquals(4, result.size());
    assertEquals("Clean Code", result.get(0).title());
    assertEquals("Design Patterns", result.get(1).title());
    assertEquals("Effective Java", result.get(2).title());
    assertEquals("Spring in Action", result.get(3).title());
  }

  @Test
  @DisplayName("findByIsbn returns book when ISBN exists")
  void findByIsbn_returnsBookWhenExists() {
    when(bookRepository.findByIsbn("ISBN-002")).thenReturn(Optional.of(sampleBooks.get(1)));

    var result = bookService.findByIsbn("ISBN-002");

    assertTrue(result.isPresent());
    assertEquals("Clean Code", result.get().title());
  }

  @Test
  @DisplayName("findByIsbn returns empty when ISBN not found")
  void findByIsbn_returnsEmptyWhenNotFound() {
    when(bookRepository.findByIsbn("ISBN-999")).thenReturn(Optional.empty());

    var result = bookService.findByIsbn("ISBN-999");

    assertTrue(result.isEmpty());
  }

  @Test
  @DisplayName("getCatalogSummary returns correct statistics")
  void getCatalogSummary_returnsCorrectStats() {
    when(bookRepository.findAll()).thenReturn(sampleBooks);

    var summary = bookService.getCatalogSummary();

    assertNotNull(summary);
    assertEquals(4, summary.totalBooks());
    assertEquals(3L, summary.availableCount()); // 3 available books
    assertEquals(3, summary.categories().size()); // Java, Programming, Architecture
    assertTrue(summary.categories().contains("Java"));
  }
}
