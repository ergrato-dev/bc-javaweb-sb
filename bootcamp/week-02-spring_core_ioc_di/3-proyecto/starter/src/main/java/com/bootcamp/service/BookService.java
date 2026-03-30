package com.bootcamp.service;

import com.bootcamp.model.Book;
import com.bootcamp.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Business logic for library catalog operations.
 *
 * Uses constructor injection — BookRepository is injected by Spring.
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    // TODO:
    //  1. Add constructor that receives BookRepository
    //     (Spring auto-detects single constructors — no @Autowired needed)
    //     Store the dependency in the final field above

    // TODO:
    //  2. Add @PostConstruct method that prints:
    //     "BookService initialized — catalog ready with X books"
    //     (where X is the count from bookRepository.findAll().size())

    /**
     * Returns all books sorted alphabetically by title.
     *
     * TODO:
     *  3. Call bookRepository.findAll()
     *  4. Sort by title using Comparator.comparing(Book::title)
     *  5. Return as list
     */
    public List<Book> getAllBooksSortedByTitle() {
        // TODO: Implement
        return List.of();
    }

    /**
     * Finds a book by ISBN.
     *
     * TODO:
     *  6. Delegate to bookRepository.findByIsbn(isbn)
     */
    public Optional<Book> findByIsbn(String isbn) {
        // TODO: Implement
        return Optional.empty();
    }

    /**
     * Returns a catalog summary: total books, available count, categories.
     *
     * TODO:
     *  7. Use Streams to compute:
     *     - totalBooks: bookRepository.findAll().size()
     *     - availableCount: count of books where available == true
     *     - categories: distinct categories sorted alphabetically
     *  8. Return a CatalogSummary record
     */
    public CatalogSummary getCatalogSummary() {
        // TODO: Implement
        return null;
    }

    /**
     * Summary of the library catalog.
     */
    public record CatalogSummary(
            int totalBooks,
            long availableCount,
            List<String> categories
    ) {}
}
