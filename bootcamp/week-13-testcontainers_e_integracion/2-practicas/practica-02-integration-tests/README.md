# Práctica 2: Tests de Integración Full-Stack

## 🎯 Objetivo

Escribir tests de integración que cubren flujos completos HTTP → Service → PostgreSQL.

---

## Paso 1: @SpringBootTest con TestRestTemplate

`TestRestTemplate` hace peticiones HTTP reales (sin mock) contra un servidor en puerto aleatorio:

```java
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
class ProductApiTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17-alpine");

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createProduct_returns201() {
        var request = new ProductCreateRequest("Widget", "SKU-001", 9.99, 10);
        var response = restTemplate.postForEntity("/api/products", request, ProductResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().id()).isNotNull();
    }
}
```

**Descomenta `PASO 1`** en `starter/IntegrationApp.java`.

---

## Paso 2: Flujo completo CRUD

Un test de integración verifica toda la secuencia HTTP:

1. Create → 201
2. Read → 200, datos correctos
3. Update → 200
4. Delete → 204
5. Read deleted → 404

**Descomenta `PASO 2`**.

---

## Paso 3: @Sql para datos de prueba

Cuando necesitas datos pre-existentes (búsquedas, filtros):

```java
@Test
@Sql("/sql/test-products.sql")
void searchProducts_returnsMatches() {
    var response = restTemplate.getForEntity(
        "/api/products?name=widget", ProductPage.class);
    assertThat(response.getBody().content()).hasSizeGreaterThan(0);
}
```

**Descomenta `PASO 3`**.

---

## Paso 4: AbstractIntegrationTest — Clase base reutilizable

Para no repetir la configuración de Testcontainers en cada clase:

```java
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
abstract class AbstractIntegrationTest {
    @Container @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17-alpine");
}

// Tests heredan la configuración
class MyTest extends AbstractIntegrationTest {
    @Autowired TestRestTemplate rest;
    @Test void myTest() { ... }
}
```

**Descomenta `PASO 4`** y observa cuánto código se elimina.

