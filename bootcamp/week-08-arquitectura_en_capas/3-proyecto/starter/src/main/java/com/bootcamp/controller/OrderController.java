package com.bootcamp.controller;

import com.bootcamp.domain.OrderStatus;
import com.bootcamp.dto.*;
import com.bootcamp.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * REST Controller for Order management.
 *
 * TODO:
 *  1. Add @RestController and @RequestMapping("/api/orders")
 *  2. Add @Tag(name = "Orders") for Swagger
 *  3. Add constructor with OrderService
 *
 *  4. GET /api/orders
 *     @Operation(summary = "List orders with optional filters and pagination")
 *     @RequestParam(required=false) Long customerId
 *     @RequestParam(required=false) OrderStatus status
 *     Pageable pageable
 *     Return 200 Page<OrderSummaryResponse>
 *
 *  5. GET /api/orders/{id}
 *     @Operation(summary = "Get order details with items")
 *     Return 200 OrderResponse
 *
 *  6. POST /api/orders
 *     @Operation(summary = "Create a new order")
 *     @Valid @RequestBody OrderCreateRequest
 *     Return 201 Created with Location header
 *
 *  7. PUT /api/orders/{id}/status
 *     @Operation(summary = "Update order status")
 *     @Valid @RequestBody OrderUpdateRequest
 *     Return 200 OrderResponse
 *
 *  8. DELETE /api/orders/{id}
 *     @Operation(summary = "Cancel an order")
 *     Return 204 No Content
 */
public class OrderController {
    // TODO: Implement
}
