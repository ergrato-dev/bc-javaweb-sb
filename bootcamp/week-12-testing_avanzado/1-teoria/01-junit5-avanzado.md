# JUnit 5: Características Avanzadas

## 🎯 Objetivos

- Usar `@ParameterizedTest` para eliminar tests repetitivos
- Organizar tests con `@Nested` para mayor legibilidad
- Controlar el orden de ejecución con `@TestMethodOrder`
- Usar `@DisplayName` para documentar el comportamiento

---

## 1. @ParameterizedTest — Múltiples entradas, un test

```java
@ParameterizedTest(name = "ISBN {0} should be valid: {1}")
@CsvSource({
    "978-0-596-51774-8, true",
    "978-0-13-468599-1, true",
    "invalid-isbn, false",
    "'', false"
})
void isValidIsbn(String isbn, boolean expected) {
    assertThat(IsbnValidator.isValid(isbn)).isEqualTo(expected);
}
```

```java
// Fuentes de datos disponibles:
@ValueSource(ints = {1, 2, 3, 5, 8, 13})          // primitivos/strings
@EnumSource(value = BookStatus.class,
            names = {"AVAILABLE", "RESERVED"})     // enum values
@MethodSource("provideBookRequests")               // método estático
@CsvFileSource(resources = "/test-data.csv")       // archivo CSV
```

```java
// @MethodSource — para objetos complejos
static Stream<Arguments> provideBookRequests() {
    return Stream.of(
        Arguments.of("Clean Code", "9780132350884", 29.99, true),
        Arguments.of("", "9780132350884", 29.99, false),       // título vacío
        Arguments.of("Clean Code", "invalid", 29.99, false),   // ISBN inválido
        Arguments.of("Clean Code", "9780132350884", -1.0, false) // precio negativo
    );
}

@ParameterizedTest
@MethodSource("provideBookRequests")
void createBook_validatesInput(String title, String isbn, double price, boolean valid) {
    // ...
}
```

---

## 2. @Nested — Organizar tests por escenario

```java
@DisplayName("BookService tests")
class BookServiceTest {

    @Nested
    @DisplayName("findById")
    class FindById {

        @Test
        @DisplayName("returns book when it exists")
        void returnsBook() { ... }

        @Test
        @DisplayName("throws BookNotFoundException when not found")
        void throwsWhenNotFound() { ... }
    }

    @Nested
    @DisplayName("create")
    class Create {

        @Test
        @DisplayName("saves book with all fields")
        void savesBook() { ... }

        @Test
        @DisplayName("throws when ISBN already exists")
        void throwsOnDuplicateIsbn() { ... }
    }
}
```

---

## 3. @TestMethodOrder — Orden de ejecución

```java
// Útil para tests de integración con estado compartido
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookInventoryIntegrationTest {

    @Test
    @Order(1)
    void addBook() { ... }

    @Test
    @Order(2)
    void loanBook() { ... }

    @Test
    @Order(3)
    void returnBook() { ... }
}
```

---

## 4. Assertions Avanzadas

```java
// AssertJ — API fluida más expresiva que JUnit assertions
assertThat(book.getTitle()).isEqualTo("Clean Code");
assertThat(books).hasSize(3).extracting(Book::getTitle)
                             .containsExactly("A", "B", "C");

// Exception assertions
assertThatThrownBy(() -> service.findById(999L))
    .isInstanceOf(BookNotFoundException.class)
    .hasMessageContaining("999");

// Soft assertions — reporta TODOS los fallos de una vez
SoftAssertions.assertSoftly(softly -> {
    softly.assertThat(book.getTitle()).isEqualTo("Expected Title");
    softly.assertThat(book.getIsbn()).isEqualTo("Expected ISBN");
    softly.assertThat(book.getPrice()).isEqualTo(BigDecimal.TEN);
});
```

---

## 5. @TempDir y @ExtendWith

```java
// @TempDir — directorio temporal que se limpia después del test
@Test
void generatesReport(@TempDir Path tempDir) {
    var reportFile = tempDir.resolve("report.csv");
    reportService.generateCsvReport(reportFile);
    assertThat(reportFile).exists().isNotEmpty();
}

// @ExtendWith — agregar extensiones a la clase de test
@ExtendWith(MockitoExtension.class)  // activa Mockito
@ExtendWith(MyCustomExtension.class) // extensión personalizada
class MyTest { ... }
```

---

## ✅ Checklist

- [ ] `@ParameterizedTest` con `@CsvSource` para validaciones de input
- [ ] `@Nested` para agrupar por método o escenario
- [ ] `@DisplayName` en todas las clases y métodos de test
- [ ] AssertJ para assertions expresivas (en lugar de JUnit assertions)
- [ ] `assertThatThrownBy()` para verificar excepciones
