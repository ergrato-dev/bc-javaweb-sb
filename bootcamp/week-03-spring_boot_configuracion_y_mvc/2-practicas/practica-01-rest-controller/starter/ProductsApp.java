package com.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Práctica 01 — Primer REST Controller
 *
 * Todo en un solo archivo para simplificar.
 * Instrucciones: descomenta cada sección en orden.
 */
@SpringBootApplication
public class ProductsApp {
  public static void main(String[] args) {
    SpringApplication.run(ProductsApp.class, args);
  }

  // ============================================
  // STEP 1: Record DTOs
  // Descomenta las siguientes líneas:
  // ============================================

  // record ProductResponse(Long id, String name, String category, double price)
  // {}
  // record ProductRequest(String name, String category, double price) {}

  // ============================================
  // Service (ya implementado — solo para la práctica)
  // Descomenta cuando tengas STEP 1:
  // ============================================

  // @Service
  // static class ProductService {
  // private final AtomicLong counter = new AtomicLong(1);
  // private final List<ProductResponse> store = new ArrayList<>(List.of(
  // new ProductResponse(counter.getAndIncrement(), "Laptop", "Electronics",
  // 999.00),
  // new ProductResponse(counter.getAndIncrement(), "Desk", "Furniture", 299.00),
  // new ProductResponse(counter.getAndIncrement(), "Keyboard", "Electronics",
  // 79.99),
  // new ProductResponse(counter.getAndIncrement(), "Chair", "Furniture", 199.00)
  // ));
  //
  // public List<ProductResponse> findAll() { return List.copyOf(store); }
  //
  // public Optional<ProductResponse> findById(Long id) {
  // return store.stream().filter(p -> p.id().equals(id)).findFirst();
  // }
  //
  // public List<ProductResponse> findByCategory(String category) {
  // return store.stream()
  // .filter(p -> p.category().equalsIgnoreCase(category))
  // .toList();
  // }
  //
  // public ProductResponse create(ProductRequest request) {
  // var product = new ProductResponse(counter.getAndIncrement(),
  // request.name(), request.category(), request.price());
  // store.add(product);
  // return product;
  // }
  // }

  // ============================================
  // STEP 2: Controller base + GET all
  // Descomenta la clase y STEP 2:
  // ============================================

  // @RestController
  // @RequestMapping("/api/products")
  // static class ProductController {
  //
  // private final ProductService productService;
  //
  // public ProductController(ProductService productService) {
  // this.productService = productService;
  // }
  //
  // // STEP 2: GET /api/products
  // // @GetMapping
  // // public List<ProductResponse> getAll() {
  // // return productService.findAll();
  // // }
  //
  // // STEP 3: GET /api/products/{id}
  // // @GetMapping("/{id}")
  // // public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
  // // return productService.findById(id)
  // // .map(ResponseEntity::ok)
  // // .orElse(ResponseEntity.notFound().build());
  // // }
  //
  // // STEP 4: GET /api/products?category=xxx
  // // @GetMapping // cuidado — colisiona con STEP 2, deberás unificar ambos
  // // public List<ProductResponse> search(
  // // @RequestParam(required = false) String category) {
  // // if (category != null) {
  // // return productService.findByCategory(category);
  // // }
  // // return productService.findAll();
  // // }
  //
  // // STEP 5: POST /api/products
  // // @PostMapping
  // // public ResponseEntity<ProductResponse> create(
  // // @RequestBody ProductRequest request) {
  // // var created = productService.create(request);
  // // var location = URI.create("/api/products/" + created.id());
  // // return ResponseEntity.created(location).body(created);
  // // }
  // }
}
