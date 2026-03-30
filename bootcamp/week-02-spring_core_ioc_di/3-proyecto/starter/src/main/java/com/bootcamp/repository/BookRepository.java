package com.bootcamp.repository;

import com.bootcamp.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * In-memory repository for Book data.
 * In a production app this would use Spring Data JPA.
 */
@Repository
public class BookRepository {

    // Simulated in-memory storage (no DB yet — covered in week 05)
    private final List<Book> books = List.of(
        new Book("978-0-13-468599-1", "Effective Java",            "Joshua Bloch",        "Programming", true),
        new Book("978-0-13-235088-4", "Clean Code",                "Robert C. Martin",    "Programming", true),
        new Book("978-0-20-163361-5", "The Pragmatic Programmer",  "David Thomas",        "Programming", false),
        new Book("978-0-59-651798-1", "Spring in Action",          "Craig Walls",         "Java",        true),
        new Book("978-0-13-110362-7", "The C Programming Language","Brian Kernighan",     "Programming", true),
        new Book("978-1-49-195029-6", "Java Concurrency in Practice","Brian Goetz",       "Java",        false)
    );

    /**
     * Returns all books in the catalog.
     *
     * TODO:
     *  1. Return the list of all books
     */
    public List<Book> findAll() {
        // TODO: Return books list
        return List.of();
    }

    /**
     * Finds a book by its ISBN.
     *
     * TODO:
     *  1. Stream over books
     *  2. Filter by isbn equality
     *  3. Return findFirst() (returns Optional<Book>)
     */
    public Optional<Book> findByIsbn(String isbn) {
        // TODO: Implement using stream().filter().findFirst()
        return Optional.empty();
    }

    /**
     * Returns only available books.
     *
     * TODO:
     *  1. Stream over books
     *  2. Filter by available == true
     *  3. Return as list
     */
    public List<Book> findAvailable() {
        // TODO: Implement using stream().filter().toList()
        return List.of();
    }

    /**
     * Returns books by category (case-insensitive).
     *
     * TODO:
     *  1. Stream over books
     *  2. Filter by category ignoring case
     *  3. Return as list
     */
    public List<Book> findByCategory(String category) {
        // TODO: Implement using stream().filter().toList()
        return List.of();
    }
}
