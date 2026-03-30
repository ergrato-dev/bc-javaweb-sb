# Testcontainers: Tests de Integración con DB Real

## 🎯 Objetivos

- Entender por qué H2 no es suficiente para tests de integración
- Configurar Testcontainers con PostgreSQL
- Usar `@SpringBootTest` con una DB real en Docker

---

## 1. El Problema con H2

```yaml
# H2: rápido pero diferente a PostgreSQL
spring.datasource.url: jdbc:h2:mem:testdb;MODE=PostgreSQL

# Diferencias que causan bugs silenciosos:
# - JSONB, arrays, UUID nativo: no funciona en H2
# - Funciones propias de PostgreSQL: to_tsvector, generate_series
# - Constraints sutilmente diferentes
# - ILIKE, SIMILAR TO con comportamientos distintos
```

**Testcontainers** levanta un contenedor Docker real de PostgreSQL antes de los tests y lo destruye al terminar. Sin instalación, sin configuración manual.

---

## 2. Dependencias

```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-testcontainers</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>postgresql</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

---

## 3. Configuración con @ServiceConnection (Spring Boot 3.1+)

```java
// La forma moderna — sin application.yml especial para tests
@SpringBootTest
@Testcontainers
class BookRepositoryIntegrationTest {

    // Spring Boot detecta automáticamente el tipo y configura el DataSource
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
        new PostgreSQLContainer<>("postgres:17-alpine");

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findByIsbn_worksWithRealPostgres() {
        var saved = bookRepository.save(
            new Book(null, "Clean Code", "9780132350884", "Martin", BD(29.99), 2)
        );

        var found = bookRepository.findByIsbn("9780132350884");

        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(saved.getId());
    }
}
```

---

## 4. @DataJpaTest con Testcontainers

```java
// @DataJpaTest es más rápido que @SpringBootTest porque solo carga JPA
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductQueryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
        new PostgreSQLContainer<>("postgres:17-alpine");

    @Autowired
    private TestEntityManager em;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void searchByName_isCaseInsensitive() {
        em.persistAndFlush(new Product("Laptop Pro", 999.99));
        em.persistAndFlush(new Product("Desktop Plus", 599.99));

        // ILIKE es una función real de PostgreSQL (no existe en H2)
        var results = productRepository.searchByName("laptop");
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getName()).isEqualTo("Laptop Pro");
    }
}
```

---

## 5. @SpringBootTest — Integración full-stack

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class OrderApiIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
        new PostgreSQLContainer<>("postgres:17-alpine");

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DirtiesContext  // limpia el contexto entre tests (reset DB)
    void createOrderAndRetrieveIt() {
        var request = new OrderCreateRequest("user1", List.of(
            new OrderItemRequest(1L, 2)
        ));

        var created = restTemplate.postForEntity(
            "/api/orders", request, OrderResponse.class);

        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        var id = created.getBody().id();

        var found = restTemplate.getForEntity(
            "/api/orders/" + id, OrderResponse.class);

        assertThat(found.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(found.getBody().username()).isEqualTo("user1");
    }
}
```

---

## ✅ Checklist

- [ ] Dependencias de Testcontainers en `pom.xml` (scope: test)
- [ ] Driver PostgreSQL en scope: runtime
- [ ] `@Container @ServiceConnection` con `PostgreSQLContainer`
- [ ] `@AutoConfigureTestDatabase(replace = NONE)` para `@DataJpaTest`
- [ ] Docker corriendo antes de ejecutar los tests
