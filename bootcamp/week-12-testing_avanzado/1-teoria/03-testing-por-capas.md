# Testing por Capas en Spring Boot

## 🎯 Objetivos

- Elegir el slice de test adecuado para cada capa
- Usar `@WebMvcTest` para controllers con `MockMvc`
- Usar `@DataJpaTest` para repositories con H2
- Entender la pirámide de tests

---

## 1. La Pirámide de Tests

```
         /\
        /  \
       / E2E \       ← pocos, lentos, frágiles
      /--------\
     /Integration\   ← algunos, @SpringBootTest
    /------------\
   /  Unit Tests  \  ← muchos, rápidos, aislados
  /--------------/
```

| Tipo | Anotación | Contexto | Velocidad |
|---|---|---|---|
| Unit | `@ExtendWith(MockitoExtension)` | Ninguno | ⚡ Rápido |
| Slice Controller | `@WebMvcTest` | Solo MVC | ⚡ Rápido |
| Slice Repository | `@DataJpaTest` | Solo JPA | 🔶 Medio |
| Integración | `@SpringBootTest` | Completo | 🐢 Lento |

---

## 2. @WebMvcTest — Solo la capa web

```java
// Solo carga: Controllers, @ControllerAdvice, Filters MVC
// No carga: Services, Repositories, @Component genérico
@WebMvcTest(BookController.class)
@Import(SecurityConfig.class)      // si necesitas seguridad
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean                        // ← siempre @MockBean (no @Mock)
    private BookService bookService;

    @Test
    void getBook_returns200() throws Exception {
        var book = new BookResponse(1L, "Clean Code", "9780132350884");
        when(bookService.findById(1L)).thenReturn(book);

        mockMvc.perform(get("/api/books/1")
                .with(user("admin").roles("USER"))) // seguridad en tests
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Clean Code"))
            .andExpect(jsonPath("$.isbn").value("9780132350884"));
    }

    @Test
    void createBook_returns201() throws Exception {
        var request = new BookCreateRequest("Clean Code", "9780132350884", 29.99);
        var response = new BookResponse(1L, "Clean Code", "9780132350884");
        when(bookService.create(any())).thenReturn(response);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .with(user("admin").roles("ADMIN")))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", containsString("/api/books/1")));
    }

    @Test
    void createBook_returns400_whenInvalidInput() throws Exception {
        var invalid = new BookCreateRequest("", "bad-isbn", -1.0); // todos inválidos

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalid))
                .with(user("admin").roles("ADMIN")))
            .andExpect(status().isBadRequest());
    }
}
```

---

## 3. @DataJpaTest — Solo la capa de persistencia

```java
// Solo carga: Entidades JPA, Repositories, Flyway/Liquibase, DataSource (H2)
// No carga: Services, Controllers, @Component
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // usar PostgreSQL real (Testcontainers)
class BookRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findByIsbn_returnsBook() {
        var book = em.persistAndFlush(
            new Book(null, "Clean Code", "9780132350884", 29.99, BookStatus.AVAILABLE)
        );

        var found = bookRepository.findByIsbn("9780132350884");

        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Clean Code");
    }

    @Test
    void findByStatus_returnsOnlyAvailable() {
        em.persistAndFlush(new Book(null, "Book A", "111", 10.0, BookStatus.AVAILABLE));
        em.persistAndFlush(new Book(null, "Book B", "222", 10.0, BookStatus.LOANED));
        em.persistAndFlush(new Book(null, "Book C", "333", 10.0, BookStatus.AVAILABLE));

        var available = bookRepository.findByStatus(BookStatus.AVAILABLE);

        assertThat(available).hasSize(2)
            .extracting(Book::getTitle)
            .containsExactlyInAnyOrder("Book A", "Book C");
    }
}
```

---

## 4. @SpringBootTest — Integración completa

```java
// Levanta el contexto completo — usar solo para tests críticos de integración
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createAndRetrieveBook() {
        var request = new BookCreateRequest("Clean Code", "9780132350884", 29.99);

        var created = restTemplate
            .withBasicAuth("admin", "admin123")
            .postForEntity("/api/books", request, BookResponse.class);

        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        var id = created.getBody().id();
        var retrieved = restTemplate
            .withBasicAuth("user", "user123")
            .getForEntity("/api/books/{id}", BookResponse.class, id);

        assertThat(retrieved.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(retrieved.getBody().title()).isEqualTo("Clean Code");
    }
}
```

---

## 5. Cobertura de Tests (JaCoCo)

```xml
<!-- pom.xml -->
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <configuration>
        <rules>
            <rule>
                <element>CLASS</element>
                <limits>
                    <limit>
                        <counter>LINE</counter>
                        <value>COVEREDRATIO</value>
                        <minimum>0.80</minimum>  <!-- 80% mínimo por clase -->
                    </limit>
                </limits>
            </rule>
        </rules>
    </configuration>
</plugin>
```

```bash
# Generar reporte de cobertura
mvn test jacoco:report

# Ver reporte en: target/site/jacoco/index.html
```

---

## ✅ Checklist

- [ ] Unit tests para services (`@ExtendWith(MockitoExtension.class)`)
- [ ] `@WebMvcTest` para cada controller (200, 201, 400, 401, 403, 404)
- [ ] `@DataJpaTest` para queries custom de repository
- [ ] `@SpringBootTest` mínimo (1-2 flujos críticos de negocio)
- [ ] JaCoCo con mínimo 80% de cobertura en services
