# Glosario — Semana 12: Testing Avanzado

## A

**ArgumentCaptor**
Clase de Mockito que captura el argumento pasado a un método de mock para hacer assertions sobre él.
```java
@Captor ArgumentCaptor<Book> captor;
verify(repo).save(captor.capture());
assertThat(captor.getValue().getTitle()).isEqualTo("Clean Code");
```

**AssertJ**
Librería de assertions con API fluida incluida en Spring Boot Test. Más expresiva que JUnit assertions nativas.
```java
assertThat(list).hasSize(3).extracting(Book::getTitle).contains("Clean Code");
```

## D

**@DataJpaTest**
Slice de test que carga solo la capa JPA: entidades, repositories y una DB embebida (H2). Ideal para testear queries custom.

**doAnswer**
Método de Mockito para configurar comportamiento dinámico en un mock, especialmente útil con métodos `void`.
```java
doAnswer(inv -> { inv.getArgument(0, Loan.class).setId(1L); return null; }).when(repo).save(any());
```

## I

**inOrder**
Verificación de que los métodos de un mock fueron llamados en un orden específico.
```java
var order = inOrder(bookRepo, loanRepo);
order.verify(bookRepo).findById(anyLong());
order.verify(loanRepo).save(any());
```

**@InjectMocks**
Anotación de Mockito que crea una instancia real de la clase bajo test e inyecta automáticamente los `@Mock` declarados.

## J

**JaCoCo**
Plugin de Maven/Gradle que mide la cobertura de código. Genera reporte HTML en `target/site/jacoco/index.html`.

## M

**MockMvc**
Herramienta de Spring Test para probar controllers web sin levantar un servidor HTTP real.
```java
mockMvc.perform(get("/api/books/1")).andExpect(status().isOk()).andExpect(jsonPath("$.title").value("Clean Code"));
```

## N

**@Nested**
Anotación de JUnit 5 para crear clases internas que agrupan tests relacionados. Mejora la legibilidad del reporte.

## P

**@ParameterizedTest**
Anotación de JUnit 5 que permite ejecutar el mismo test con múltiples conjuntos de datos.
```java
@ParameterizedTest @CsvSource({"a, true", "b, false"}) void test(String input, boolean expected) {...}
```

**Pirámide de Tests**
Estrategia de distribución de tests: muchos tests unitarios (rápidos), algunos de integración (medium), pocos E2E (lentos y frágiles).

## S

**SoftAssertions**
Variante de AssertJ que evalúa todas las assertions de un bloque aunque alguna falle, reportando todos los fallos juntos.

**Spy**
Mock parcial de un objeto real en Mockito. Ejecuta el código real excepto los métodos explícitamente stubbeados con `doReturn/doThrow`.
```java
var spy = spy(new MyService());
doReturn(42).when(spy).calculate(); // solo este método es falso
```

## T

**@TestMethodOrder**
Anotación de JUnit 5 que controla el orden de ejecución de los tests. Usar con `MethodOrderer.OrderAnnotation` y `@Order`.

## V

**verify()**
Método de Mockito que verifica que un método de mock fue (o no fue) llamado, con los argumentos especificados.
```java
verify(repo, times(1)).save(any());
verify(emailService, never()).send(anyString());
```

## W

**@WebMvcTest**
Slice de test que carga solo la capa web: controllers, `@ControllerAdvice`, filtros. Usa `MockMvc`. Los services deben ser `@MockBean`.
