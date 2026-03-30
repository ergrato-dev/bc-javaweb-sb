package com.bootcamp.inventory;

import com.bootcamp.inventory.dto.Dtos.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

/**
 * ProductApiIntegrationTest — Tests de integración full HTTP → Service →
 * PostgreSQL.
 *
 * Extiende AbstractIntegrationTest que ya configura:
 * - @SpringBootTest(webEnvironment = RANDOM_PORT)
 * - @Testcontainers
 * - @Container @ServiceConnection PostgreSQLContainer
 *
 * INSTRUCCIONES:
 * 1. Implementa los TODOs de cada test
 * 2. Cada test hace peticiones HTTP reales con TestRestTemplate
 * 3. Los datos se guardan en PostgreSQL real (Testcontainers)
 * 4. Ejecuta con: mvn test (requiere Docker)
 *
 * IMPORTANTE: Los tests de integración comparten el mismo contenedor
 * y base de datos. Usa deleteAll() en @BeforeEach para aislar los tests.
 */
@DisplayName("Product API Integration Tests")
class ProductApiIntegrationTest extends AbstractIntegrationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  // Importar el repositorio para setup/teardown
  @Autowired
  private com.bootcamp.inventory.repository.ProductRepository productRepository;

  @BeforeEach
  void setUp() {
    productRepository.deleteAll(); // aislamiento entre tests
  }

  // Helper — crea un request de prueba
  private ProductCreateRequest validRequest(String sku) {
    return new ProductCreateRequest(
        "Test Product " + sku,
        sku,
        BigDecimal.valueOf(29.99),
        50,
        "TEST");
  }

  // ============================================
  // POST /api/products
  // ============================================

  @Nested
  @DisplayName("POST /api/products")
  class Create {

    @Test
    @DisplayName("returns 201 Created with Location header")
    void returns201() {
      // TODO: Implementar
      // 1. Usar restTemplate.postForEntity("/api/products", validRequest("SKU-001"),
      // ProductResponse.class)
      // 2. Verificar status == 201
      // 3. Verificar que response.getHeaders().getLocation() no es null
      // 4. Verificar que body.id() no es null
      // 5. Verificar que body.sku() == "SKU-001"
    }

    @Test
    @DisplayName("returns 400 when request is invalid")
    void returns400OnInvalid() {
      // TODO: Implementar
      // 1. Crear un request con sku = "" (inválido)
      // 2. postForEntity con Object.class (no nos importa el tipo)
      // 3. Verificar status == 400
    }

    @Test
    @DisplayName("returns 400 when SKU already exists")
    void returns400OnDuplicateSku() {
      // TODO: Implementar
      // 1. Crear primer producto con SKU-DUP
      // 2. Intentar crear otro con el mismo SKU-DUP
      // 3. Verificar que el segundo retorna 400
    }
  }

  // ============================================
  // GET /api/products/{id}
  // ============================================

  @Nested
  @DisplayName("GET /api/products/{id}")
  class GetById {

    @Test
    @DisplayName("returns 200 with product data")
    void returns200() {
      // TODO: Implementar
      // 1. Crear un producto via POST y obtener su ID
      // 2. GET /api/products/{id}
      // 3. Verificar 200 y datos correctos
    }

    @Test
    @DisplayName("returns 404 when product not found")
    void returns404() {
      // TODO: Implementar
      // 1. GET /api/products/999999
      // 2. Verificar 404
    }
  }

  // ============================================
  // PATCH /api/products/{id}/stock/add y /remove
  // ============================================

  @Nested
  @DisplayName("Stock management")
  class StockManagement {

    @Test
    @DisplayName("addStock increases stock and returns 200")
    void addStock() {
      // TODO: Implementar
      // 1. Crear producto con stock=50
      // 2. PATCH /api/products/{id}/stock/add con {"quantity": 20}
      // TIP: usar restTemplate.patchForObject() o exchange()
      // 3. Verificar que stock == 70 en la respuesta
    }

    @Test
    @DisplayName("removeStock decreases stock and returns 200")
    void removeStock() {
      // TODO: Implementar
      // 1. Crear producto con stock=50
      // 2. PATCH /api/products/{id}/stock/remove con {"quantity": 30}
      // 3. Verificar que stock == 20
    }

    @Test
    @DisplayName("removeStock returns 409 Conflict when insufficient stock")
    void returns409WhenInsufficientStock() {
      // TODO: Implementar
      // 1. Crear producto con stock=10
      // 2. Intentar remover 50 unidades
      // 3. Verificar 409 Conflict
    }
  }

  // ============================================
  // Flujo completo
  // ============================================

  @Test
  @DisplayName("full product lifecycle: create → read → update → delete")
  void fullLifecycle() {
    // TODO: Implementar el flujo completo CRUD
    // 1. POST → 201, obtener id
    // 2. GET /{id} → 200, verificar nombre
    // 3. PUT /{id} con nuevo nombre y precio
    // 4. GET /{id} → 200, verificar que nombre cambió
    // 5. DELETE /{id} → 204
    // 6. GET /{id} → 404
  }
}
