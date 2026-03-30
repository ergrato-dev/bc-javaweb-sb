# TDD Básico y Calidad de Tests

## 🎯 Objetivos

- Aplicar el ciclo Red-Green-Refactor
- Reconocer un test de calidad vs. uno frágil
- Entender las métricas de cobertura

---

## 1. TDD: Red-Green-Refactor

```
Red   → Escribir un test que FALLA (la funcionalidad no existe)
Green → Escribir el mínimo código para que el test PASE
Refactor → Mejorar el código sin que los tests fallen
```

### Ejemplo Práctico

```java
// PASO 1: RED — Escribir el test primero
@Test
void validateIsbn_returnsTrueFor13Digits() {
    assertThat(IsbnValidator.isValid("9780132350884")).isTrue();
    assertThat(IsbnValidator.isValid("invalid")).isFalse();
    assertThat(IsbnValidator.isValid("")).isFalse();
}
// ↑ Este test FALLA porque IsbnValidator no existe todavía ✅

// PASO 2: GREEN — Implementar lo mínimo
class IsbnValidator {
    static boolean isValid(String isbn) {
        return isbn != null && isbn.matches("\\d{13}");
    }
}
// ↑ Ahora el test PASA ✅

// PASO 3: REFACTOR — Mejorar sin romper tests
class IsbnValidator {
    private static final Pattern ISBN_PATTERN = Pattern.compile("\\d{13}");

    static boolean isValid(String isbn) {
        return isbn != null && !isbn.isBlank() && ISBN_PATTERN.matcher(isbn).matches();
    }
}
// ↑ Tests siguen pasando ✅
```

---

## 2. Tests Frágiles vs. Tests Robustos

```java
// ❌ FRÁGIL — depende del orden de la DB, strings exactos de ID
@Test
void test1() {
    var books = bookRepository.findAll();
    assertThat(books.get(0).getId()).isEqualTo(1L); // frágil: depende del orden
    assertThat(books).hasSize(5);                   // frágil: cuántos hay en la DB?
}

// ✅ ROBUSTO — independiente de datos pre-existentes
@Test
@Transactional
void findAll_returnsAllSavedBooks() {
    em.persist(new Book("Book A", "1111111111111"));
    em.persist(new Book("Book B", "2222222222222"));
    em.flush();

    var found = bookRepository.findAll();

    // Solo verificamos lo que NOSOTROS insertamos
    assertThat(found)
        .extracting(Book::getIsbn)
        .contains("1111111111111", "2222222222222");
}
```

---

## 3. Nombrando Tests: El Patrón `given_when_then`

```java
// given_when_then — legible como documentación

// ❌ Nombre vago
void testCreate() { ... }

// ✅ Descriptivo — funciona como documentación viva
void givenDuplicateIsbn_whenCreate_thenThrowsIllegalArgument() { ... }

// ✅ Alternativa con @DisplayName
@Test
@DisplayName("throws IllegalArgumentException when ISBN already exists")
void throwsOnDuplicateIsbn() { ... }

// ✅ Con @Nested — la clase da el contexto
class Create {
    @Test @DisplayName("throws when ISBN already exists")
    void throwsOnDuplicateIsbn() { ... }
}
```

---

## 4. Cobertura: Qué medir y qué ignorar

```
Line Coverage:    % de líneas ejecutadas por los tests
Branch Coverage:  % de ramas del if/else/switch cubiertas
Method Coverage:  % de métodos llamados

Objetivo razonable: 80% en services y domain
NO obsesionarse con 100% — los getters/setters no necesitan tests
```

```java
// JaCoCo puede excluir clases que no necesitan tests:
// - DTOs (records con solo campos)
// - Constantes
// - Punto de entrada main()

// En pom.xml:
// <configuration>
//   <excludes>
//     <exclude>**/dto/**</exclude>
//     <exclude>**/*Application.class</exclude>
//   </excludes>
// </configuration>
```

---

## 5. Estrategia para el Proyecto Final

```
Semana 16 requiere:
  ✅ Unit tests: todos los services (@ExtendWith MockitoExtension)
  ✅ Controller tests: todos los endpoints (@WebMvcTest)
  ✅ Integration test: 1-2 flujos críticos (@SpringBootTest + Testcontainers)
  ✅ JaCoCo: ≥80% en service layer

Regla: si introduces un bug, UNO de tus tests debe fallar.
Si puedes romper el código sin que fallen los tests, los tests son incompletos.
```

---

## ✅ Checklist

- [ ] Practica TDD en al menos UN método de la semana
- [ ] Los nombres de tests describen el comportamiento esperado
- [ ] No hay tests que dependan del orden de ejecución
- [ ] JaCoCo ≥80% en la capa de service
