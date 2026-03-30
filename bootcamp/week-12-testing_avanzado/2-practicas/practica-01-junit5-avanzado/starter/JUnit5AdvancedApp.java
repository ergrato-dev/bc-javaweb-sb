package com.bootcamp.testing;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

/**
 * PRÁCTICA 1: JUnit 5 Características Avanzadas
 *
 * Instrucciones:
 * 1. Lee el README.md para entender cada paso
 * 2. Descomenta cada sección en orden
 * 3. Ejecuta los tests con: mvn test
 * 4. Verifica que todos los tests pasen antes de continuar al siguiente paso
 */
public class JUnit5AdvancedApp {

  // ============================================
  // CLASES DE SOPORTE PARA LOS EJERCICIOS
  // ============================================

  // Clase utilitaria para validación de ISBN
  static class IsbnUtils {
    static boolean isValid(String isbn) {
      if (isbn == null || isbn.isBlank())
        return false;
      // ISBN-13: 13 dígitos numéricos
      return isbn.matches("\\d{13}");
    }
  }

  // Record DTO para libro
  record BookRequest(String title, String isbn, double price) {
  }

  record BookResponse(Long id, String title, String isbn) {
  }

  // Excepción de dominio
  static class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
      super("Book not found: " + id);
    }
  }

  // Servicio simple a testear
  static class BookService {
    private final java.util.Map<Long, BookResponse> store = new java.util.HashMap<>();
    private long nextId = 1L;

    BookResponse create(BookRequest request) {
      if (request.title().isBlank())
        throw new IllegalArgumentException("Title required");
      if (!IsbnUtils.isValid(request.isbn()))
        throw new IllegalArgumentException("Invalid ISBN");
      if (request.price() <= 0)
        throw new IllegalArgumentException("Price must be positive");
      var book = new BookResponse(nextId++, request.title(), request.isbn());
      store.put(book.id(), book);
      return book;
    }

    BookResponse findById(Long id) {
      var book = store.get(id);
      if (book == null)
        throw new BookNotFoundException(id);
      return book;
    }

    java.util.List<BookResponse> findAll() {
      return new java.util.ArrayList<>(store.values());
    }
  }

  // ============================================
  // PASO 1: @ParameterizedTest con @CsvSource
  // ============================================
  // Este test verifica la validación de ISBN con múltiples entradas
  // sin duplicar código. Descomenta las siguientes líneas:

  // @Nested
  // @DisplayName("PASO 1: Parameterized Tests")
  // class Step1 {
  //
  // @ParameterizedTest(name = "ISBN '{0}' debe ser válido={1}")
  // @CsvSource({
  // "9780132350884, true",
  // "9780596517748, true",
  // "9781491950357, true",
  // "invalid-isbn, false",
  // "12345, false",
  // "'', false"
  // })
  // @DisplayName("validates ISBN format")
  // void isValidIsbn(String isbn, boolean expected) {
  // assertThat(IsbnUtils.isValid(isbn)).isEqualTo(expected);
  // }
  //
  // @ParameterizedTest(name = "precio {0} debe ser válido={1}")
  // @CsvSource({
  // "0.01, true",
  // "29.99, true",
  // "999.99, true",
  // "0.0, false",
  // "-1.0, false"
  // })
  // @DisplayName("validates price is positive")
  // void isValidPrice(double price, boolean expected) {
  // // Un precio es válido si es mayor que 0
  // assertThat(price > 0).isEqualTo(expected);
  // }
  //
  // @ParameterizedTest
  // @ValueSource(strings = {"Clean Code", "The Pragmatic Programmer",
  // "Refactoring"})
  // @DisplayName("accepts valid non-blank titles")
  // void acceptsValidTitles(String title) {
  // assertThat(title).isNotBlank();
  // }
  // }

  // ============================================
  // PASO 2: @Nested — Agrupar tests por escenario
  // ============================================
  // @Nested permite organizar tests relacionados y produce
  // un reporte de tests más legible. Descomenta:

  // @Nested
  // @DisplayName("PASO 2: Nested — BookService")
  // class Step2 {
  //
  // BookService service = new BookService();
  //
  // @Nested
  // @DisplayName("create()")
  // class Create {
  //
  // @Test
  // @DisplayName("saves book and assigns ID")
  // void savesBook() {
  // var request = new BookRequest("Clean Code", "9780132350884", 29.99);
  // var result = service.create(request);
  //
  // assertThat(result.id()).isNotNull();
  // assertThat(result.title()).isEqualTo("Clean Code");
  // assertThat(result.isbn()).isEqualTo("9780132350884");
  // }
  //
  // @Test
  // @DisplayName("throws when title is blank")
  // void throwsOnBlankTitle() {
  // var request = new BookRequest("", "9780132350884", 29.99);
  // assertThatThrownBy(() -> service.create(request))
  // .isInstanceOf(IllegalArgumentException.class)
  // .hasMessage("Title required");
  // }
  //
  // @Test
  // @DisplayName("throws when ISBN is invalid")
  // void throwsOnInvalidIsbn() {
  // var request = new BookRequest("Clean Code", "bad-isbn", 29.99);
  // assertThatThrownBy(() -> service.create(request))
  // .isInstanceOf(IllegalArgumentException.class)
  // .hasMessage("Invalid ISBN");
  // }
  // }
  //
  // @Nested
  // @DisplayName("findById()")
  // class FindById {
  //
  // @Test
  // @DisplayName("returns book when it exists")
  // void returnsBook() {
  // var created = service.create(new BookRequest("Refactoring", "9780201485677",
  // 39.99));
  // var found = service.findById(created.id());
  // assertThat(found).isEqualTo(created);
  // }
  //
  // @Test
  // @DisplayName("throws BookNotFoundException when not found")
  // void throwsWhenNotFound() {
  // assertThatThrownBy(() -> service.findById(999L))
  // .isInstanceOf(BookNotFoundException.class)
  // .hasMessageContaining("999");
  // }
  // }
  // }

  // ============================================
  // PASO 3: @TestMethodOrder — Orden de ejecución
  // ============================================
  // Cuando el estado persiste entre tests, el orden importa.
  // Esta clase interna tiene su propio contexto. Descomenta:

  // @Nested
  // @DisplayName("PASO 3: TestMethodOrder — flujo secuencial")
  // @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
  // class Step3 {
  //
  // // Estado compartido entre tests (solo válido con orden garantizado)
  // static BookService service = new BookService();
  // static Long createdId;
  //
  // @Test
  // @Order(1)
  // @DisplayName("1. Create a book")
  // void step1_createBook() {
  // var book = service.create(new BookRequest("TDD By Example", "9780321146533",
  // 34.99));
  // createdId = book.id();
  // assertThat(createdId).isNotNull();
  // assertThat(service.findAll()).hasSize(1);
  // }
  //
  // @Test
  // @Order(2)
  // @DisplayName("2. Find the created book")
  // void step2_findBook() {
  // var found = service.findById(createdId);
  // assertThat(found.title()).isEqualTo("TDD By Example");
  // }
  //
  // @Test
  // @Order(3)
  // @DisplayName("3. Verify only one book exists")
  // void step3_verifyCount() {
  // assertThat(service.findAll()).hasSize(1);
  // }
  // }

  // ============================================
  // PASO 4: assertThatThrownBy y SoftAssertions
  // ============================================
  // Descomenta:

  // @Nested
  // @DisplayName("PASO 4: Advanced Assertions")
  // class Step4 {
  //
  // BookService service = new BookService();
  //
  // @Test
  // @DisplayName("assertThatThrownBy catches exception with details")
  // void exceptionAssertion() {
  // // assertThatThrownBy es más expresivo que assertThrows
  // // porque puedes hacer chain de assertions sobre la excepción
  // assertThatThrownBy(() -> service.findById(42L))
  // .isInstanceOf(BookNotFoundException.class)
  // .hasMessageContaining("42")
  // .isNotNull();
  // }
  //
  // @Test
  // @DisplayName("SoftAssertions reports all failures at once")
  // void softAssertions() {
  // var book = service.create(new BookRequest("Clean Code", "9780132350884",
  // 29.99));
  //
  // // Con SoftAssertions, si uno falla, los demás IGUALMENTE se evalúan
  // // y todos los fallos se reportan juntos al final
  // SoftAssertions.assertSoftly(softly -> {
  // softly.assertThat(book.id()).isGreaterThan(0L);
  // softly.assertThat(book.title()).isEqualTo("Clean Code");
  // softly.assertThat(book.isbn()).startsWith("978");
  // softly.assertThat(book.isbn()).hasSize(13);
  // });
  // }
  //
  // @ParameterizedTest
  // @MethodSource("com.bootcamp.testing.JUnit5AdvancedApp#provideInvalidRequests")
  // @DisplayName("create() rejects all invalid inputs")
  // void rejectsInvalidInputs(BookRequest request, String expectedMessage) {
  // assertThatThrownBy(() -> service.create(request))
  // .isInstanceOf(IllegalArgumentException.class)
  // .hasMessage(expectedMessage);
  // }
  // }

  // Método proveedor para @MethodSource del PASO 4
  static Stream<org.junit.jupiter.params.provider.Arguments> provideInvalidRequests() {
    return Stream.of(
        org.junit.jupiter.params.provider.Arguments.of(
            new BookRequest("", "9780132350884", 29.99), "Title required"),
        org.junit.jupiter.params.provider.Arguments.of(
            new BookRequest("Clean Code", "bad-isbn", 29.99), "Invalid ISBN"),
        org.junit.jupiter.params.provider.Arguments.of(
            new BookRequest("Clean Code", "9780132350884", 0.0), "Price must be positive"));
  }
}
