package com.bootcamp.controller;

import com.bootcamp.dto.ProductRequest;
import com.bootcamp.dto.ProductResponse;
import com.bootcamp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * REST Controller for Product CRUD operations with pagination.
 *
 * TODO:
 *  1. Add @RestController and @RequestMapping("/api/products")
 *  2. Add constructor with ProductService
 *  3. GET /api/products — accepts ?category= and Pageable params — returns Page<ProductResponse>
 *  4. GET /api/products/search — accepts ?name= — returns List<ProductResponse>
 *  5. GET /api/products/{id} — returns 200 or 404
 *  6. POST /api/products — @Valid body — returns 201 Created with Location
 *  7. PUT /api/products/{id} — @Valid body — returns 200 or 404
 *  8. DELETE /api/products/{id} — returns 204 No Content
 *  9. PATCH /api/products/{id}/stock — @RequestParam int quantity — decrements stock — returns 200
 */
public class ProductController {

    // TODO: Add service field and constructor

    // TODO: Implement endpoints
}
