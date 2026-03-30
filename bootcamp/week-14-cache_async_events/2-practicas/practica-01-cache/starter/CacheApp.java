package com.bootcamp.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CacheApp — Demostración de Spring Cache
 *
 * Descomenta cada sección en el orden indicado.
 * Para ejecutar: mvn spring-boot:run (no se usa, es standalone demo)
 *
 * application.yml recomendado:
 * spring:
 * cache:
 * type: simple
 */

// ============================================
// PASO 1: Habilitar @EnableCaching
// ============================================
// Descomenta las siguientes anotaciones:

// @SpringBootApplication
// @EnableCaching // ← activa el soporte de caché en toda la aplicación
@Slf4j
public class CacheApp {

  public static void main(String[] args) {
    SpringApplication.run(CacheApp.class, args);
  }

  // ============================================
  // PASO 2: @Cacheable — evitar consultas repetidas
  // ============================================
  // Descomenta las líneas del método findProduct():

  // Imagina que este método hace una consulta a BD (lenta)
  // @Cacheable(value = "products", key = "#id")
  // public String findProduct(Long id) {
  // log.info("Consultando BD para producto {}", id); // solo aparece en cache
  // miss
  // return "Product-" + id;
  // }

  // ============================================
  // PASO 3: @CacheEvict — invalidar al actualizar
  // ============================================
  // Descomenta las líneas del método updateProduct():

  // @CacheEvict(value = "products", key = "#id")
  // public String updateProduct(Long id, String newName) {
  // log.info("Actualizando producto {} → invalidando caché", id);
  // return newName; // en producción: guardar en BD y retornar DTO
  // }

  // ============================================
  // PASO 4: @CachePut — actualizar sin saltarse el método
  // ============================================
  // Descomenta las líneas del método createProduct():

  // Diferencia con @Cacheable: @CachePut SIEMPRE ejecuta el método
  // y actualiza el caché con el resultado
  // @CachePut(value = "products", key = "#id")
  // public String createProduct(Long id, String name) {
  // log.info("Creando producto {} — se guarda en BD y en caché", id);
  // return name;
  // }
}
