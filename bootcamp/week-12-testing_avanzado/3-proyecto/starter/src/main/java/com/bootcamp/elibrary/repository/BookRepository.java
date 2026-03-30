package com.bootcamp.elibrary.repository;

import com.bootcamp.elibrary.domain.Book;
import com.bootcamp.elibrary.domain.BookStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    Page<Book> findByStatus(BookStatus status, Pageable pageable);
}
