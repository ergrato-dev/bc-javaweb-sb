package com.bootcamp.model;

/**
 * Represents a book in the library catalog.
 *
 * @param isbn      unique identifier (International Standard Book Number)
 * @param title     book title
 * @param author    author full name
 * @param category  genre or subject category
 * @param available whether the book is available for borrowing
 */
public record Book(
        String isbn,
        String title,
        String author,
        String category,
        boolean available
) {
    public Book {
        if (isbn == null || isbn.isBlank())   throw new IllegalArgumentException("isbn required");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("title required");
    }
}
