package com.bootcamp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Práctica 02 — Manejo Global de Excepciones
 *
 * Instrucciones: descomenta cada sección en orden.
 */
@SpringBootApplication
public class ExceptionHandlerApp {
    public static void main(String[] args) {
        SpringApplication.run(ExceptionHandlerApp.class, args);
    }

    record ProductResponse(Long id, String name, double price) {}

    // ============================================
    // STEP 1: Excepción personalizada
    // Descomenta las siguientes líneas:
    // ============================================

    // static class ResourceNotFoundException extends RuntimeException {
    //     public ResourceNotFoundException(String resource, Long id) {
    //         super(resource + " not found with id: " + id);
    //     }
    // }

    // ============================================
    // STEP 2: Record de error estandarizado
    // Descomenta las siguientes líneas:
    // ============================================

    // record ErrorResponse(int status, String error, String message, String path) {}

    // ============================================
    // STEP 3: @RestControllerAdvice — handler global
    // Descomenta las siguientes líneas:
    // ============================================

    // @RestControllerAdvice
    // static class GlobalExceptionHandler {
    //
    //     @ExceptionHandler(ResourceNotFoundException.class)
    //     @ResponseStatus(HttpStatus.NOT_FOUND)
    //     ErrorResponse handleNotFound(ResourceNotFoundException ex,
    //                                   HttpServletRequest request) {
    //         return new ErrorResponse(404, "Not Found", ex.getMessage(), request.getRequestURI());
    //     }
    //
    //     // STEP 5: handler para validación (400 Bad Request)
    //     // @ExceptionHandler(IllegalArgumentException.class)
    //     // @ResponseStatus(HttpStatus.BAD_REQUEST)
    //     // ErrorResponse handleBadRequest(IllegalArgumentException ex,
    //     //                                HttpServletRequest request) {
    //     //     return new ErrorResponse(400, "Bad Request", ex.getMessage(), request.getRequestURI());
    //     // }
    // }

    // ============================================
    // Service con STEP 4 — lanzar ResourceNotFoundException
    // Descomenta las siguientes líneas:
    // ============================================

    // @Service
    // static class ProductService {
    //     private final List<ProductResponse> products = List.of(
    //         new ProductResponse(1L, "Laptop",   999.00),
    //         new ProductResponse(2L, "Phone",    599.00),
    //         new ProductResponse(3L, "Keyboard",  79.99)
    //     );
    //
    //     public List<ProductResponse> findAll() {
    //         return products;
    //     }
    //
    //     // STEP 4: lanzar excepción cuando no encuentra
    //     public ProductResponse findById(Long id) {
    //         // Descomenta para validar input:
    //         // if (id <= 0) throw new IllegalArgumentException("ID must be positive");
    //
    //         return products.stream()
    //                 .filter(p -> p.id().equals(id))
    //                 .findFirst()
    //                 .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    //     }
    // }

    // Controller (ya listo)
    // @RestController
    // @RequestMapping("/api/products")
    // static class ProductController {
    //     private final ProductService productService;
    //
    //     public ProductController(ProductService productService) {
    //         this.productService = productService;
    //     }
    //
    //     @GetMapping
    //     public List<ProductResponse> getAll() {
    //         return productService.findAll();
    //     }
    //
    //     @GetMapping("/{id}")
    //     public ProductResponse getById(@PathVariable Long id) {
    //         return productService.findById(id);  // lanza excepción si no existe
    //     }
    // }
}
