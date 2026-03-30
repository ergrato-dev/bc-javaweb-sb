package com.bootcamp.review.controller;

import com.bootcamp.review.domain.Order;
import com.bootcamp.review.dto.CreateOrderRequest;
import com.bootcamp.review.dto.OrderResponse;
import com.bootcamp.review.repository.OrderRepository;
import com.bootcamp.review.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public OrderController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    // ============================================
    // PASO 1: N+1 Problem — GET /orders/customer/{customerId}
    // ============================================
    // Este método tiene N+1: por cada Order carga los items en una query separada.
    // El fix está en OrderRepository.java — descomenta el método con JOIN FETCH
    // y reemplaza la llamada a findByCustomerId() por findByCustomerIdWithItems()
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Long customerId) {
        // ❌ N+1: Hibernate ejecuta 1 query para orders + 1 query POR CADA order para items
        return ResponseEntity.ok(orderRepository.findByCustomerId(customerId));
    }

    // ============================================
    // PASO 2: Lógica de negocio en el Controller
    // ============================================
    // ❌ INCORRECTO — el controller calcula el total y construye la entidad
    // Descomenta el método correcto debajo y comenta este.
    @PostMapping
    public ResponseEntity<Order> createOrderBad(@RequestBody CreateOrderRequest req) {
        if (req.items().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        double total = req.items().stream()
                .mapToDouble(item -> item.price() * item.quantity())
                .sum();
        var order = new Order();
        order.setCustomerId(req.customerId());
        order.setTotal(total);
        return ResponseEntity.ok(orderRepository.save(order));
    }

    // ✅ CORRECTO — descomenta para reemplazar el método anterior:
    // @PostMapping
    // public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest req) {
    //     return ResponseEntity.status(201).body(orderService.create(req));
    // }
}
