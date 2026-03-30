package com.bootcamp.testcontainers;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.*;

/**
 * PRÁCTICA 1: Testcontainers Setup
 *
 * Requisito: Docker debe estar corriendo (`docker ps`)
 *
 * Instrucciones:
 * 1. Lee el README.md para entender cada paso
 * 2. Descomenta cada sección en orden
 * 3. Ejecuta con: mvn test
 * 4. Observa los logs: Docker descargará postgres:17-alpine la primera vez
 */
public class TestcontainersApp {

  // ============================================
  // CLASES DE SOPORTE
  // ============================================

  @Entity
  @Table(name = "tc_products")
  static class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String sku;
    double price;
    int stock;

    protected Product() {
    }

    Product(String name, String sku, double price) {
      this.name = name;
      this.sku = sku;
      this.price = price;
      this.stock = 0;
    }
  }

  interface ProductRepository extends JpaRepository<Product, Long> {
  }

  // ============================================
  // PASO 1: Verificar que tenemos Testcontainers
  // ============================================
  // Este test NO usa Docker — solo verifica que la clase PostgreSQLContainer
  // existe.
  // Descomenta:

  // @Nested
  // @DisplayName("PASO 1: Testcontainers available")
  // class Step1 {
  //
  // @Test
  // @DisplayName("PostgreSQLContainer class is available on classpath")
  // void testcontainersIsOnClasspath() {
  // // Si Testcontainers está en el classpath, este test pasa sin Docker
  // assertThat(PostgreSQLContainer.class).isNotNull();
  // assertThat(new PostgreSQLContainer<>("postgres:17-alpine")).isNotNull();
  // }
  // }

  // ============================================
  // PASO 2 + 3: @SpringBootTest con PostgreSQL real
  // ============================================
  // Este test SÍ requiere Docker.
  // La primera vez descarga postgres:17-alpine (~100MB), luego queda en caché.
  // Descomenta:

  // @SpringBootTest
  // @Testcontainers
  // @DisplayName("PASO 2+3: SpringBootTest with real PostgreSQL")
  // static class Step2And3 {
  //
  // // @Container — Testcontainers gestiona el ciclo de vida del contenedor
  // // @ServiceConnection — Spring Boot configura el DataSource automáticamente
  // @Container
  // @ServiceConnection
  // static PostgreSQLContainer<?> postgres =
  // new PostgreSQLContainer<>("postgres:17-alpine");
  //
  // @Autowired
  // private ProductRepository productRepository;
  //
  // @Test
  // @DisplayName("container starts and JPA works with real PostgreSQL")
  // void containerStartsAndJpaWorks() {
  // // Este test usa la base de datos PostgreSQL real en Docker
  // var product = productRepository.save(new Product("Widget A", "SKU-001",
  // 9.99));
  //
  // assertThat(product.id).isNotNull();
  // assertThat(product.id).isGreaterThan(0L);
  //
  // var found = productRepository.findById(product.id);
  // assertThat(found).isPresent();
  // assertThat(found.get().name).isEqualTo("Widget A");
  // assertThat(found.get().sku).isEqualTo("SKU-001");
  // }
  //
  // @Test
  // @DisplayName("findAll returns persisted data")
  // void findAllReturnsSavedData() {
  // productRepository.deleteAll(); // limpiar estado previo
  //
  // productRepository.save(new Product("Product A", "A001", 10.0));
  // productRepository.save(new Product("Product B", "B001", 20.0));
  //
  // var all = productRepository.findAll();
  // assertThat(all).hasSize(2)
  // .extracting(p -> p.name)
  // .containsExactlyInAnyOrder("Product A", "Product B");
  // }
  // }

  // ============================================
  // PASO 4: @DataJpaTest con Testcontainers
  // ============================================
  // @DataJpaTest carga solo JPA — más rápido que @SpringBootTest
  // @AutoConfigureTestDatabase(replace = NONE) — NO usar H2, usar nuestro
  // contenedor
  // Descomenta:

  // @DataJpaTest
  // @Testcontainers
  // @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
  // @DisplayName("PASO 4: DataJpaTest with Testcontainers (faster)")
  // static class Step4 {
  //
  // @Container
  // @ServiceConnection
  // static PostgreSQLContainer<?> postgres =
  // new PostgreSQLContainer<>("postgres:17-alpine");
  //
  // @Autowired
  // private TestEntityManager em;
  //
  // @Autowired
  // private ProductRepository productRepository;
  //
  // @Test
  // @DisplayName("TestEntityManager persists and finds entity")
  // void persistAndFind() {
  // // TestEntityManager — wrapper conveniente para persistir en tests
  // var product = em.persistAndFlush(
  // new Product("Test Product", "TEST-001", 15.99)
  // );
  //
  // em.clear(); // limpia el cache de JPA para forzar lectura desde DB
  //
  // var found = productRepository.findById(product.id);
  // assertThat(found).isPresent();
  // assertThat(found.get().name).isEqualTo("Test Product");
  //
  // System.out.println("✅ Test passed with real PostgreSQL!");
  // }
  // }
}
