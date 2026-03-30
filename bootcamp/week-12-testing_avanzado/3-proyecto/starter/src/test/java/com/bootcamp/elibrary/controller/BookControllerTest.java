package com.bootcamp.elibrary.controller;

import com.bootcamp.elibrary.dto.Dtos.*;
import com.bootcamp.elibrary.exception.BookNotFoundException;
import com.bootcamp.elibrary.exception.GlobalExceptionHandler;
import com.bootcamp.elibrary.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * BookControllerTest — Escribe aquí los tests para BookController.
 *
 * INSTRUCCIONES:
 * 1. Implementa cada método de test reemplazando // TODO
 * 2. Usa mockMvc.perform() + expect() para verificar HTTP responses
 * 3. Usa jsonPath() para verificar el body de la respuesta
 * 4. Ejecuta con: mvn test
 *
 * MÉTODOS ÚTILES MockMvc:
 *   get("/api/books"), post("/api/books"), put("/api/books/1"), delete("/api/books/1")
 *   .contentType(MediaType.APPLICATION_JSON)
 *   .content(objectMapper.writeValueAsString(request))
 *
 * MÉTODOS ÚTILES MockMvcResultMatchers:
 *   status().isOk(), .isCreated(), .isNotFound(), .isBadRequest(), .isNoContent()
 *   jsonPath("$.title").value("Clean Code")
 *   jsonPath("$").isArray()
 *   header().exists("Location")
 */
@WebMvcTest(BookController.class)
@Import(GlobalExceptionHandler.class)
@DisplayName("BookController")
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    // Helper — crea una BookResponse de prueba
    private BookResponse makeResponse(Long id, String title) {
        return new BookResponse(id, title, "978" + String.format("%010d", id),
                               "Test Author", BigDecimal.valueOf(29.99),
                               com.bootcamp.elibrary.domain.BookStatus.AVAILABLE, 2, 2);
    }

    // ============================================
    // GET /api/books
    // ============================================

    @Nested
    @DisplayName("GET /api/books")
    class GetAll {

        @Test
        @DisplayName("returns 200 with list of books")
        void returns200() throws Exception {
            // TODO: Implementar
            // 1. Stubbear bookService.findAll(any()) →
            //    new PageImpl<>(List.of(makeResponse(1L, "Clean Code"), makeResponse(2L, "Refactoring")))
            // 2. mockMvc.perform(get("/api/books"))
            // 3. Verificar: status 200, $.content es array, $.content[0].title == "Clean Code"
        }
    }

    // ============================================
    // GET /api/books/{id}
    // ============================================

    @Nested
    @DisplayName("GET /api/books/{id}")
    class GetById {

        @Test
        @DisplayName("returns 200 with book when found")
        void returns200() throws Exception {
            // TODO: Implementar
            // 1. Stubbear findById(1L) → makeResponse(1L, "Clean Code")
            // 2. Verificar: 200, $.id == 1, $.title == "Clean Code"
        }

        @Test
        @DisplayName("returns 404 when book not found")
        void returns404() throws Exception {
            // TODO: Implementar
            // 1. Stubbear findById(99L) → throw BookNotFoundException(99L)
            // 2. Verificar: 404
        }
    }

    // ============================================
    // POST /api/books
    // ============================================

    @Nested
    @DisplayName("POST /api/books")
    class CreateBook {

        @Test
        @DisplayName("returns 201 Created with Location header")
        void returns201() throws Exception {
            // TODO: Implementar
            // 1. Crear BookCreateRequest("Clean Code", "9780132350884", "Author", BigDecimal.valueOf(29.99), 2)
            // 2. Stubbear bookService.create(any()) → makeResponse(1L, "Clean Code")
            // 3. Verificar: 201, header Location existe, $.title == "Clean Code"
        }

        @Test
        @DisplayName("returns 400 when request has validation errors")
        void returns400OnInvalidInput() throws Exception {
            // TODO: Implementar
            // 1. Crear BookCreateRequest con título vacío y isbn "bad"
            // 2. Verificar: 400 (la validación ocurre antes de llegar al servicio)
            // 3. Verificar que bookService.create() nunca fue llamado:
            //    verify(bookService, never()).create(any())
        }

        @Test
        @DisplayName("returns 409 Conflict when ISBN already exists")
        void returns409OnDuplicateIsbn() throws Exception {
            // TODO: Implementar
            // 1. Request válido
            // 2. Stubbear create() → throw IllegalArgumentException("Book with ISBN... already exists")
            // 3. Verificar: 400 (IllegalArgumentException → 400 en GlobalExceptionHandler)
        }
    }

    // ============================================
    // PUT /api/books/{id}
    // ============================================

    @Nested
    @DisplayName("PUT /api/books/{id}")
    class UpdateBook {

        @Test
        @DisplayName("returns 200 with updated book")
        void returns200() throws Exception {
            // TODO: Implementar
            // 1. Crear BookUpdateRequest("Updated Title", BigDecimal.TEN)
            // 2. Stubbear update(1L, any()) → makeResponse(1L, "Updated Title")
            // 3. Verificar: 200, $.title == "Updated Title"
        }

        @Test
        @DisplayName("returns 404 when book not found")
        void returns404() throws Exception {
            // TODO: Implementar
        }
    }

    // ============================================
    // DELETE /api/books/{id}
    // ============================================

    @Nested
    @DisplayName("DELETE /api/books/{id}")
    class DeleteBook {

        @Test
        @DisplayName("returns 204 No Content when deleted")
        void returns204() throws Exception {
            // TODO: Implementar
            // 1. Stubbear delete(1L) → doNothing()
            // 2. Verificar: 204
        }

        @Test
        @DisplayName("returns 404 when book not found")
        void returns404() throws Exception {
            // TODO: Implementar
            // 1. Stubbear delete(99L) → throw BookNotFoundException(99L)
            // 2. Verificar: 404
        }
    }
}
