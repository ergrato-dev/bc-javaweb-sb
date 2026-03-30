package com.bootcamp.elibrary.controller;

import com.bootcamp.elibrary.dto.Dtos.*;
import com.bootcamp.elibrary.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public Page<BookResponse> findAll(Pageable pageable) {
    return bookService.findAll(pageable);
  }

  @GetMapping("/{id}")
  public BookResponse findById(@PathVariable Long id) {
    return bookService.findById(id);
  }

  @PostMapping
  public ResponseEntity<BookResponse> create(@Valid @RequestBody BookCreateRequest request) {
    var created = bookService.create(request);
    var location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(created.id()).toUri();
    return ResponseEntity.created(location).body(created);
  }

  @PutMapping("/{id}")
  public BookResponse update(@PathVariable Long id,
      @Valid @RequestBody BookUpdateRequest request) {
    return bookService.update(id, request);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    bookService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
