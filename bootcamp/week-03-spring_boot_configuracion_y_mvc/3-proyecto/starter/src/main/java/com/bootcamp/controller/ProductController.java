package com.bootcamp.controller;

import com.bootcamp.dto.ProductRequest;
import com.bootcamp.dto.ProductResponse;
import com.bootcamp.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * REST Controller for Product CRUD operations.
 *
 * Base URL: /api/products
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  // TODO:
  // 1. Add constructor injection of ProductService

  // TODO:
  // 2. Implement GET /api/products
  // - Optional @RequestParam category (nullable)
  // - Return 200 with List<ProductResponse>

  // TODO:
  // 3. Implement GET /api/products/{id}
  // - Return 200 with ProductResponse
  // - ProductService throws ProductNotFoundException if not found → 404

  // TODO:
  // 4. Implement POST /api/products
  // - @RequestBody ProductRequest
  // - Return 201 Created with Location header: /api/products/{id}
  // - Body: created ProductResponse

  // TODO:
  // 5. Implement PUT /api/products/{id}
  // - @RequestBody ProductRequest
  // - Return 200 with updated ProductResponse
  // - Or 404 if not found

  // TODO:
  // 6. Implement DELETE /api/products/{id}
  // - Return 204 No Content
  // - Or 404 if not found
}
