package com.bootcamp.elibrary.service;

import com.bootcamp.elibrary.domain.Book;
import com.bootcamp.elibrary.domain.BookStatus;
import com.bootcamp.elibrary.dto.Dtos.*;
import com.bootcamp.elibrary.exception.BookNotFoundException;
import com.bootcamp.elibrary.repository.BookRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * BookServiceTest — Escribe aquí los tests para BookService.
 *
 * INSTRUCCIONES:
 * 1. Implementa cada método de test reemplazando el comentario // TODO
 * 2. Usa @ParameterizedTest donde se pide
 * 3. Usa ArgumentCaptor donde se pide
 * 4. Ejecuta los tests con: mvn test
 * 5. Objetivo: 100% de cobertura de BookService
 *
 * CLASES DISPONIBLES:
 * - BookService (la clase bajo test — NO la modifiques)
 * - BookRepository (mock)
 * - Dtos.BookCreateRequest, BookUpdateRequest, BookResponse
 * - BookNotFoundException
 *
 * MÉTODO UTILITARIO:
 * - makeBook(id, title) → crea un Book de prueba con valores por defecto
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("BookService")
class BookServiceTest {

  @Mock
  private BookRepository bookRepository;

  @InjectMocks
  private BookService bookService;

  @Captor
  private ArgumentCaptor<Book> bookCaptor;

  // Método utilitario — crea un Book de prueba
  private Book makeBook(Long id, String title) {
    return new Book(id, title, "978" + String.format("%010d", id),
        "Test Author", BigDecimal.valueOf(29.99), 2);
  }

  // ============================================
  // findAll()
  // ============================================

  @Nested
  @DisplayName("findAll()")
  class FindAll {

    @Test
    @DisplayName("returns page of books")
    void returnsPageOfBooks() {
      // TODO: Implementar
      // 1. Crear lista con 2 libros usando makeBook()
      // 2. Stubbear bookRepository.findAll(Pageable) → new PageImpl<>(list)
      // 3. Llamar bookService.findAll(Pageable.unpaged())
      // 4. Verificar que el resultado tiene 2 elementos
      // 5. Verificar que los títulos son los correctos usando
      // extracting(BookResponse::title)
    }

    @Test
    @DisplayName("returns empty page when no books")
    void returnsEmptyPage() {
      // TODO: Implementar
      // 1. Stubbear bookRepository.findAll(any(Pageable.class)) → Page.empty()
      // 2. Llamar bookService.findAll(Pageable.unpaged())
      // 3. Verificar que el resultado está vacío
    }
  }

  // ============================================
  // findById()
  // ============================================

  @Nested
  @DisplayName("findById()")
  class FindById {

    @Test
    @DisplayName("returns book response when found")
    void returnsBook() {
      // TODO: Implementar
      // 1. Crear un Book con makeBook(1L, "Clean Code")
      // 2. Stubbear bookRepository.findById(1L) → Optional.of(book)
      // 3. Llamar bookService.findById(1L)
      // 4. Verificar id, title, isbn del resultado
    }

    @Test
    @DisplayName("throws BookNotFoundException when not found")
    void throwsWhenNotFound() {
      // TODO: Implementar
      // 1. Stubbear bookRepository.findById(99L) → Optional.empty()
      // 2. Verificar que bookService.findById(99L) lanza BookNotFoundException
      // 3. Verificar que el mensaje contiene "99"
      // TIP: usar assertThatThrownBy()
    }
  }

  // ============================================
  // create()
  // ============================================

  @Nested
  @DisplayName("create()")
  class Create {

    @Test
    @DisplayName("saves book and returns response with all fields")
    void savesBook() {
      // TODO: Implementar
      // 1. Crear un BookCreateRequest con título, isbn "9780132350884", autor, precio
      // 30.0, copies 3
      // 2. Stubbear existsByIsbn → false
      // 3. Stubbear bookRepository.save(any()) → usar thenAnswer para retornar el
      // argumento
      // con id asignado: thenAnswer(inv -> { Book b = inv.getArgument(0); /* set id
      // via reflection o sub-test */ return b; })
      // ALTERNATIVA: crear el Book esperado y retornarlo con thenReturn
      // 4. Llamar bookService.create(request)
      // 5. Verificar que el resultado tiene los mismos datos del request
    }

    @Test
    @DisplayName("throws when ISBN already exists")
    void throwsOnDuplicateIsbn() {
      // TODO: Implementar
      // 1. Crear un BookCreateRequest
      // 2. Stubbear existsByIsbn → true
      // 3. Verificar que create() lanza IllegalArgumentException con mensaje sobre
      // ISBN
      // 4. Verificar que bookRepository.save() NUNCA fue llamado
      // TIP: usar verify(bookRepository, never()).save(any())
    }

    @Test
    @DisplayName("captures book with correct fields when saving")
    void capturesBookFields() {
      // TODO: Implementar — usar ArgumentCaptor
      // 1. Crear BookCreateRequest("Effective Java", "9780134685991", "Joshua Bloch",
      // 49.99, 5)
      // 2. Stubbear existsByIsbn → false
      // 3. Stubbear save() → retornar el argumento (thenAnswer)
      // 4. Ejecutar bookService.create(request)
      // 5. Capturar el Book con bookCaptor:
      // verify(bookRepository).save(bookCaptor.capture())
      // 6. Verificar: título, isbn, autor, precio, totalCopies == 5 del Book
      // capturado
    }

    @Test
    @DisplayName("@ParameterizedTest — rejects invalid create requests")
    void rejectsInvalidRequests() {
      // TODO: Implementar con @ParameterizedTest
      //
      // Cambiar la anotación @Test a @ParameterizedTest y agregar @CsvSource:
      // - título vacío: "", "9780132350884", "Author", 29.99, 1
      // - isbn corto: "title", "123", "Author", 29.99, 1
      // - copies=0: "title", "9780132350884", "Author", 29.99, 0
      // - precio negativo: "title", "9780132350884", "Author", -1.0, 1
      //
      // Para este test, la VALIDACIÓN sucede en el Controller con @Valid,
      // pero BookService también puede lanzar excepciones para isbn duplicado.
      // En cambio, este test verifica que EL REPOSITORIO no es llamado
      // cuando existsByIsbn retorna true.

      // VERSIÓN SIMPLIFICADA — verifica que duplicate isbn nunca guarda:
      var request = new BookCreateRequest("Clean Code", "9780132350884",
          "Author", BigDecimal.valueOf(29.99), 1);
      when(bookRepository.existsByIsbn("9780132350884")).thenReturn(true);

      assertThatThrownBy(() -> bookService.create(request))
          .isInstanceOf(IllegalArgumentException.class);
      verify(bookRepository, never()).save(any());
    }
  }

  // ============================================
  // update()
  // ============================================

  @Nested
  @DisplayName("update()")
  class Update {

    @Test
    @DisplayName("updates title and price")
    void updatesTitleAndPrice() {
      // TODO: Implementar
      // 1. Crear un Book con makeBook(1L, "Old Title")
      // 2. Stubbear findById(1L) → Optional.of(book)
      // 3. Stubbear save(any()) → retornar el argumento
      // 4. Llamar bookService.update(1L, new BookUpdateRequest("New Title",
      // BigDecimal.TEN))
      // 5. Verificar que el resultado tiene título "New Title" y precio 10
    }

    @Test
    @DisplayName("throws BookNotFoundException when book not found")
    void throwsWhenNotFound() {
      // TODO: Implementar igual que findById's throwsWhenNotFound
    }
  }

  // ============================================
  // delete()
  // ============================================

  @Nested
  @DisplayName("delete()")
  class Delete {

    @Test
    @DisplayName("calls deleteById when book exists")
    void deletesBook() {
      // TODO: Implementar
      // 1. Stubbear existsById(1L) → true
      // 2. Llamar bookService.delete(1L)
      // 3. Verificar que deleteById(1L) fue llamado exactamente una vez
    }

    @Test
    @DisplayName("throws BookNotFoundException when book does not exist")
    void throwsWhenNotFound() {
      // TODO: Implementar
      // 1. Stubbear existsById(99L) → false
      // 2. Verificar que delete(99L) lanza BookNotFoundException
      // 3. Verificar que deleteById() nunca fue llamado
    }
  }
}
