package com.bootcamp.integration;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

/**
 * PRÁCTICA 2: Tests de Integración Full-Stack
 *
 * Requisito: Docker corriendo + la API debe estar configurada (usa el proyecto
 * de la semana)
 *
 * Los tests comentados son PATRONES a descomentar y adaptar al proyecto de la
 * semana.
 * En clase, se aplicarán a la Inventory API del proyecto semana 13.
 */
public class IntegrationApp {

  // Records de ejemplo para los tests
  record ProductCreateRequest(String name, String sku, BigDecimal price, int stock) {
  }

  record ProductUpdateRequest(String name, BigDecimal price) {
  }

  record ProductResponse(Long id, String name, String sku, BigDecimal price, int stock) {
  }

  // ============================================
  // PASO 1: TestRestTemplate — HTTP real sin mock
  // ============================================
  // Descomenta para ver cómo TestRestTemplate hace peticiones reales:

  // @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
  // @Testcontainers
  // @DisplayName("PASO 1: TestRestTemplate básico")
  // static class Step1 {
  //
  // @Container
  // @ServiceConnection
  // static PostgreSQLContainer<?> postgres = new
  // PostgreSQLContainer<>("postgres:17-alpine");
  //
  // @Autowired
  // private TestRestTemplate restTemplate;
  //
  // @Test
  // @DisplayName("GET /api/products returns 200")
  // void getProducts_returns200() {
  // var response = restTemplate.getForEntity("/api/products", Object.class);
  // // Un endpoint de listado siempre retorna 200 (con lista vacía si no hay
  // datos)
  // assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  // }
  //
  // @Test
  // @DisplayName("GET /api/products/999 returns 404")
  // void getNonExistent_returns404() {
  // var response = restTemplate.getForEntity("/api/products/999999",
  // Object.class);
  // assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  // }
  // }

  // ============================================
  // PASO 2: Flujo completo CRUD
  // ============================================
  // Descomenta:

  // @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
  // @Testcontainers
  // @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
  // @DisplayName("PASO 2: CRUD integration flow")
  // static class Step2 {
  //
  // @Container
  // @ServiceConnection
  // static PostgreSQLContainer<?> postgres = new
  // PostgreSQLContainer<>("postgres:17-alpine");
  //
  // @Autowired
  // private TestRestTemplate rest;
  //
  // static Long createdId;
  //
  // @Test
  // @Order(1)
  // @DisplayName("1. POST → 201 Created")
  // void step1_create() {
  // var request = new ProductCreateRequest("Widget Pro", "SKU-WIDGET-001",
  // BigDecimal.valueOf(29.99), 100);
  // ResponseEntity<ProductResponse> response =
  // rest.postForEntity("/api/products", request, ProductResponse.class);
  //
  // assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
  // assertThat(response.getHeaders().getLocation()).isNotNull();
  // createdId = response.getBody().id();
  // assertThat(createdId).isNotNull();
  // }
  //
  // @Test
  // @Order(2)
  // @DisplayName("2. GET /{id} → 200 with correct data")
  // void step2_read() {
  // var response = rest.getForEntity("/api/products/" + createdId,
  // ProductResponse.class);
  //
  // assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  // assertThat(response.getBody().name()).isEqualTo("Widget Pro");
  // assertThat(response.getBody().stock()).isEqualTo(100);
  // }
  //
  // @Test
  // @Order(3)
  // @DisplayName("3. PUT /{id} → 200 updated")
  // void step3_update() {
  // var update = new ProductUpdateRequest("Widget Pro v2",
  // BigDecimal.valueOf(34.99));
  // rest.put("/api/products/" + createdId, update);
  //
  // var updated = rest.getForEntity("/api/products/" + createdId,
  // ProductResponse.class);
  // assertThat(updated.getBody().name()).isEqualTo("Widget Pro v2");
  // assertThat(updated.getBody().price()).isEqualByComparingTo(BigDecimal.valueOf(34.99));
  // }
  //
  // @Test
  // @Order(4)
  // @DisplayName("4. DELETE /{id} → 204 No Content")
  // void step4_delete() {
  // rest.delete("/api/products/" + createdId);
  //
  // var notFound = rest.getForEntity("/api/products/" + createdId, Object.class);
  // assertThat(notFound.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  // }
  // }

  // ============================================
  // PASO 3: @Sql — datos de prueba para búsquedas
  // ============================================
  // Descomenta:

  // @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
  // @Testcontainers
  // @DisplayName("PASO 3: @Sql test data")
  // static class Step3 {
  //
  // @Container
  // @ServiceConnection
  // static PostgreSQLContainer<?> postgres = new
  // PostgreSQLContainer<>("postgres:17-alpine");
  //
  // @Autowired
  // private TestRestTemplate rest;
  //
  // @Test
  // @Sql("/sql/test-products.sql")
  // @Sql(scripts = "/sql/clean-products.sql",
  // executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  // @DisplayName("GET with param filters by name")
  // void search_filtersResults() {
  // var response = rest.getForEntity(
  // "/api/products?name=widget", Object.class);
  // assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  // // El SQL file inserta 3 widgets y 2 laptops
  // // Verificar que solo retorna los widgets
  // }
  // }

  // ============================================
  // PASO 4: AbstractIntegrationTest — clase base
  // ============================================
  // En proyectos reales, centraliza la configuración de Testcontainers.
  // Descomenta para ver el patrón:

  // @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
  // @Testcontainers
  // static abstract class AbstractIntegrationTest {
  // @Container
  // @ServiceConnection
  // static PostgreSQLContainer<?> postgres = new
  // PostgreSQLContainer<>("postgres:17-alpine");
  // }
  //
  // @DisplayName("PASO 4: Extending AbstractIntegrationTest")
  // static class Step4 extends AbstractIntegrationTest {
  //
  // @Autowired
  // private TestRestTemplate rest;
  //
  // @Test
  // @DisplayName("inherits Postgres container from base class")
  // void inheritsContainer() {
  // // No necesitamos declarar @Container aquí — viene de la clase base
  // var response = rest.getForEntity("/api/products", Object.class);
  // assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  // }
  // }
}
