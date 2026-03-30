package com.bootcamp.inventory.repository;

import com.bootcamp.inventory.domain.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.domain.Pageable;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

/**
 * ProductRepositoryTest — Tests de repositorio con Testcontainers
 * + @DataJpaTest.
 *
 * ¿Por qué @DataJpaTest en lugar de @SpringBootTest?
 * - Carga solo la capa JPA (más rápido)
 * - Perfecto para testear queries custom (searchByName, findByCategory, etc.)
 * - @Transactional por defecto → rollback automático entre tests
 *
 * INSTRUCCIONES:
 * 1. El contenedor de Postgres arrancará automáticamente (requiere Docker)
 * 2. Implementa los TODOs de cada test
 * 3. Ejecuta con: mvn test
 */
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("ProductRepository")
class ProductRepositoryTest {

  // @DataJpaTest usa este contenedor porque @AutoConfigureTestDatabase(replace =
  // NONE)
  // le dice a Spring que NO substituya el datasource
  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17-alpine");

  @Autowired
  private TestEntityManager em;

  @Autowired
  private ProductRepository productRepository;

  // Helper — crea y persiste un producto de prueba
  private Product saveProduct(String name, String sku, String category) {
    return em.persistAndFlush(
        new Product(null, name, sku, BigDecimal.valueOf(29.99), 10, category));
  }

  // ============================================
  // findBySku
  // ============================================

  @Nested
  @DisplayName("findBySku()")
  class FindBySku {

    @Test
    @DisplayName("returns product when sku exists")
    void returnsProduct() {
      // TODO: Implementar
      // 1. Usar saveProduct("Widget", "SKU-001", "ELECTRONICS")
      // 2. Llamar productRepository.findBySku("SKU-001")
      // 3. Verificar que está presente y tiene sku == "SKU-001"
    }

    @Test
    @DisplayName("returns empty when sku does not exist")
    void returnsEmpty() {
      // TODO: Implementar
      // 1. Llamar findBySku("NONEXISTENT")
      // 2. Verificar que Optional está vacío
    }
  }

  // ============================================
  // existsBySku
  // ============================================

  @Nested
  @DisplayName("existsBySku()")
  class ExistsBySku {

    @Test
    @DisplayName("returns true when sku exists")
    void returnsTrueWhenExists() {
      // TODO: Implementar
    }

    @Test
    @DisplayName("returns false when sku does not exist")
    void returnsFalseWhenMissing() {
      // TODO: Implementar
    }
  }

  // ============================================
  // searchByName (custom JPQL query)
  // ============================================

  @Nested
  @DisplayName("searchByName()")
  class SearchByName {

    @Test
    @DisplayName("finds products containing name (case-insensitive)")
    void findsCaseInsensitive() {
      // TODO: Implementar — este test verifica la query JPQL custom
      // 1. saveProduct("Laptop Pro", "LAP-001", "ELECTRONICS")
      // 2. saveProduct("Desktop PC", "DES-001", "ELECTRONICS")
      // 3. saveProduct("Laptop Air", "LAP-002", "ELECTRONICS")
      // 4. Buscar "laptop" (minúsculas)
      // 5. Verificar que retorna 2 productos (Laptop Pro y Laptop Air)
      // 6. Verificar que NO incluye Desktop PC
    }

    @Test
    @DisplayName("returns empty page when no match")
    void returnsEmptyWhenNoMatch() {
      // TODO: Implementar
      // 1. saveProduct("Widget", "W-001", "MISC")
      // 2. Buscar "nonexistent"
      // 3. Verificar que la página está vacía
    }
  }

  // ============================================
  // findByCategory
  // ============================================

  @Nested
  @DisplayName("findByCategory()")
  class FindByCategory {

    @Test
    @DisplayName("returns only products in given category")
    void returnsProductsInCategory() {
      // TODO: Implementar
      // 1. saveProduct("Widget A", "W-A", "TOOLS")
      // 2. saveProduct("Widget B", "W-B", "TOOLS")
      // 3. saveProduct("Laptop", "L-001", "ELECTRONICS")
      // 4. findByCategory("TOOLS", Pageable.unpaged())
      // 5. Verificar tamaño == 2 y que ambos son TOOLS
    }
  }
}
