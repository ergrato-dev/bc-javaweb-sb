package com.bootcamp.inventory;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Clase base para todos los tests de integración.
 * Declara el contenedor de PostgreSQL UNA sola vez para toda la suite.
 * Spring Boot reutiliza el contexto entre tests (@SpringBootTest).
 *
 * No modificar esta clase.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
abstract class AbstractIntegrationTest {

  // static → un solo contenedor para todos los tests (más eficiente)
  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17-alpine")
      .withDatabaseName("inventorytest")
      .withUsername("test")
      .withPassword("test");
}
