package com.bootcamp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Práctica 02 — SpringDoc OpenAPI y Swagger UI
 *
 * Instrucciones: descomenta cada sección en orden.
 */
@SpringBootApplication
public class OpenApiApp {
    public static void main(String[] args) {
        SpringApplication.run(OpenApiApp.class, args);
    }

    // ============================================
    // STEP 5: Personalizar el bean OpenAPI global
    // Descomenta este bloque de configuración:
    // ============================================

    // @Configuration
    // static class OpenApiConfig {
    //     @Bean
    //     public OpenAPI bookstoreOpenAPI() {
    //         return new OpenAPI()
    //                 .info(new Info()
    //                         .title("Bookstore API")
    //                         .version("1.0")
    //                         .description("REST API for managing bookstore inventory"));
    //     }
    // }

    record BookRequest(
        @NotBlank(message = "Title is required") String title,
        @NotBlank(message = "Author is required") String author,
        @Positive(message = "Price must be positive") double price
    ) {}

    record BookResponse(Long id, String title, String author, double price) {}

    @Service
    static class BookService {
        private final AtomicLong counter = new AtomicLong(1);
        private final List<BookResponse> store = new ArrayList<>(List.of(
            new BookResponse(counter.getAndIncrement(), "Clean Code",      "Robert Martin", 35.99),
            new BookResponse(counter.getAndIncrement(), "Spring in Action","Craig Walls",   44.99)
        ));

        public List<BookResponse> findAll() { return List.copyOf(store); }

        public BookResponse create(BookRequest req) {
            var book = new BookResponse(counter.getAndIncrement(), req.title(), req.author(), req.price());
            store.add(book);
            return book;
        }
    }

    // ============================================
    // STEP 2: Agregar @Tag al Controller
    // Descomenta la anotación @Tag:
    // ============================================

    @RestController
    @RequestMapping("/api/books")
    // @Tag(name = "Books", description = "Operations for managing bookstore inventory")
    static class BookController {
        private final BookService bookService;

        public BookController(BookService bookService) {
            this.bookService = bookService;
        }

        // ============================================
        // STEP 3: Agregar @Operation al método getAll
        // Descomenta las anotaciones:
        // ============================================

        // @Operation(summary = "Get all books", description = "Returns the complete list of books in inventory")
        // @ApiResponse(responseCode = "200", description = "List of books")
        @GetMapping
        public List<BookResponse> getAll() { return bookService.findAll(); }

        // ============================================
        // STEP 4: Documentar POST con respuestas múltiples
        // Descomenta las anotaciones:
        // ============================================

        // @Operation(summary = "Add a new book")
        // @ApiResponse(responseCode = "201", description = "Book created successfully")
        // @ApiResponse(responseCode = "400", description = "Invalid request data")
        @PostMapping
        public ResponseEntity<BookResponse> create(@Valid @RequestBody BookRequest request) {
            var created = bookService.create(request);
            return ResponseEntity
                    .created(URI.create("/api/books/" + created.id()))
                    .body(created);
        }
    }
}
