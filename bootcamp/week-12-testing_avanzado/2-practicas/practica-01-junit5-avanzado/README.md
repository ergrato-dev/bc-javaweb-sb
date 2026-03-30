# Práctica 1: JUnit 5 Características Avanzadas

## 🎯 Objetivo

Aplicar `@ParameterizedTest`, `@Nested`, `@TestMethodOrder` y assertions de AssertJ en tests unitarios reales.

---

## Estructura del ejercicio

```
practica-01-junit5-avanzado/
└── starter/
    └── JUnit5AdvancedApp.java   ← archivo único con todo el código
```

---

## Paso 1: @ParameterizedTest con @CsvSource

Los tests parametrizados eliminan la duplicación cuando necesitas probar el mismo comportamiento con múltiples inputs.

Ejemplo de referencia:

```java
// Validar un ISBN de 13 dígitos
@ParameterizedTest(name = "'{0}' debe ser válido: {1}")
@CsvSource({
    "9780132350884, true",
    "9780596517748, true",
    "invalid, false",
    "'', false"
})
void isbn_validation(String isbn, boolean expected) {
    assertThat(IsbnUtils.isValid(isbn)).isEqualTo(expected);
}
```

**Abre `starter/JUnit5AdvancedApp.java`** y descomenta la sección `PASO 1`.

---

## Paso 2: @Nested — Agrupar tests por escenario

`@Nested` permite crear clases internas que agrupan tests relacionados, mejorando la legibilidad del reporte.

Ejemplo de referencia:

```java
@DisplayName("BookService")
class BookServiceTest {

    @Nested
    @DisplayName("when book exists")
    class WhenBookExists {
        @Test void canFind() { ... }
        @Test void canUpdate() { ... }
    }

    @Nested
    @DisplayName("when book does not exist")
    class WhenBookNotFound {
        @Test void throwsOnFind() { ... }
        @Test void throwsOnUpdate() { ... }
    }
}
```

**Descomenta la sección `PASO 2`**.

---

## Paso 3: @TestMethodOrder y @Order

Controla el orden de ejecución cuando los tests tienen dependencias (útil en integración).

```java
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoanFlowTest {
    @Test @Order(1) void addBook() { ... }
    @Test @Order(2) void loanBook() { ... }
    @Test @Order(3) void returnBook() { ... }
}
```

**Descomenta la sección `PASO 3`**.

---

## Paso 4: assertThatThrownBy y SoftAssertions

```java
// Exception assertions — mucho más expresivo que assertThrows
assertThatThrownBy(() -> service.findById(999L))
    .isInstanceOf(BookNotFoundException.class)
    .hasMessageContaining("999");

// SoftAssertions — reporta TODOS los fallos, no solo el primero
assertSoftly(softly -> {
    softly.assertThat(book.getTitle()).isEqualTo("Expected");
    softly.assertThat(book.getIsbn()).startsWith("978");
});
```

**Descomenta la sección `PASO 4`** y ejecuta los tests con `mvn test`.

