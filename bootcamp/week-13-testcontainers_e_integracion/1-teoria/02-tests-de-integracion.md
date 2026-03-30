# Tests de Integración: Estrategias y Patrones

## 🎯 Objetivos

- Diseñar tests de integración que verifiquen flujos completos
- Usar `@Sql` para preparar estado de la DB
- Manejar aislamiento entre tests

---

## 1. Preparar Datos con @Sql

```java
@SpringBootTest
@Testcontainers
class InventoryIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17-alpine");

    @Autowired
    private TestRestTemplate restTemplate;

    // Insertar datos ANTES del test, limpiar DESPUÉS
    @Test
    @Sql("/sql/test-inventory.sql")              // ejecuta antes
    @Sql(scripts = "/sql/clean-inventory.sql",   // ejecuta después
         executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getStock_returnsInventoryData() {
        var resp = restTemplate.getForEntity("/api/inventory/PROD-001", StockResponse.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody().quantity()).isEqualTo(100);
    }
}
```

```sql
-- src/test/resources/sql/test-inventory.sql
INSERT INTO products (sku, name, price) VALUES ('PROD-001', 'Widget A', 9.99);
INSERT INTO inventory (product_sku, quantity) VALUES ('PROD-001', 100);
```

---

## 2. @Transactional en Tests — Rollback Automático

```java
// Cada test se ejecuta en una transacción que se hace ROLLBACK al terminar
// → la DB queda limpia para el siguiente test SIN necesitar @Sql de limpieza
@DataJpaTest
@Transactional  // default en @DataJpaTest — cada test hace rollback
class LoanRepositoryTest {

    @Autowired TestEntityManager em;
    @Autowired LoanRepository loanRepository;

    @Test
    void countActiveLoans_returnsCorrectCount() {
        // Datos creados aquí se revierten al terminar el test
        var user = em.persist(new User("alice@test.com"));
        var book = em.persist(new Book("Clean Code", "978..."));
        em.persist(new Loan("alice", book, 14));
        em.persist(new Loan("alice", book, 7));
        em.flush();

        var count = loanRepository.countByUsernameAndStatus("alice", LoanStatus.ACTIVE);

        assertThat(count).isEqualTo(2);
    }
}
```

---

## 3. Tests de Integración Full HTTP

```java
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
class FullFlowTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17-alpine");

    @Autowired
    private TestRestTemplate rest;

    @Test
    void fullProductLifecycle() {
        // 1. CREATE
        var create = new ProductCreateRequest("Widget", "SKU-001", 9.99, 50);
        var created = rest.postForEntity("/api/products", create, ProductResponse.class);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        var id = created.getBody().id();

        // 2. READ
        var found = rest.getForEntity("/api/products/" + id, ProductResponse.class);
        assertThat(found.getBody().name()).isEqualTo("Widget");
        assertThat(found.getBody().stock()).isEqualTo(50);

        // 3. UPDATE
        var update = new ProductUpdateRequest("Widget Pro", 12.99);
        rest.put("/api/products/" + id, update);

        // 4. VERIFY UPDATE
        var updated = rest.getForEntity("/api/products/" + id, ProductResponse.class);
        assertThat(updated.getBody().name()).isEqualTo("Widget Pro");

        // 5. DELETE
        rest.delete("/api/products/" + id);

        // 6. VERIFY DELETED
        var deleted = rest.getForEntity("/api/products/" + id, Object.class);
        assertThat(deleted.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
```

---

## 4. Custom Test Configuration

```java
// Clase base para reutilizar configuración de Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
abstract class AbstractIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
        new PostgreSQLContainer<>("postgres:17-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");
}

// Tests concretos extienden la base — no repiten configuración
class ProductIntegrationTest extends AbstractIntegrationTest {
    @Autowired private TestRestTemplate rest;

    @Test
    void createProduct() { ... }
}
```

---

## 5. Cuándo usar cada tipo

| Escenario | Tipo de test | Anotación |
|---|---|---|
| Lógica de negocio pura | Unit test | `@ExtendWith(MockitoExtension)` |
| Query custom JPA | Repository test | `@DataJpaTest + TC` |
| Validación + response | Controller test | `@WebMvcTest` |
| Flujo completo crítico | Integration test | `@SpringBootTest + TC` |
| E2E con frontend | E2E | Cypress / Playwright |

---

## ✅ Checklist

- [ ] Clase base abstracta para no repetir configuración de Testcontainers
- [ ] `@Sql` para datos de prueba (o `@Transactional` para rollback automático)
- [ ] Tests de integración solo para flujos críticos de negocio
- [ ] Docker corriendo (`docker ps`) antes de `mvn test`
