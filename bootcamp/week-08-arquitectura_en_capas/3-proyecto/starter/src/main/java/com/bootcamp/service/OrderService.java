package com.bootcamp.service;

import com.bootcamp.domain.*;
import com.bootcamp.dto.*;
import com.bootcamp.exception.*;
import com.bootcamp.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {

  private final OrderRepository orderRepository;
  private final CustomerRepository customerRepository;
  private final ProductRepository productRepository;

  public OrderService(OrderRepository orderRepository,
      CustomerRepository customerRepository,
      ProductRepository productRepository) {
    this.orderRepository = orderRepository;
    this.customerRepository = customerRepository;
    this.productRepository = productRepository;
  }

  /**
   * TODO:
   * 1. Build a Specification from optional customerId and status parameters.
   * 2. Call orderRepository.findAll(spec, pageable)
   * 3. Map each Order to OrderSummaryResponse using toSummary()
   * 4. Return the Page<OrderSummaryResponse>
   */
  public Page<OrderSummaryResponse> findAll(Long customerId, OrderStatus status, Pageable pageable) {
    // TODO: Implement
    return Page.empty(pageable);
  }

  /**
   * TODO:
   * 1. findByIdWithDetails(id) — load Order with customer + items + products in
   * one query
   * 2. Throw OrderNotFoundException if not found
   * 3. Map to OrderResponse using toResponse()
   */
  public OrderResponse findById(Long id) {
    // TODO: Implement
    return null;
  }

  /**
   * TODO:
   * 1. Load Customer (throw CustomerNotFoundException if not found)
   * 2. Create new Order(customer, request.notes())
   * 3. For each OrderItemRequest in request.items():
   * a. Load Product (throw ProductNotFoundException if not found)
   * b. Check product is active and has sufficient stock, throw
   * IllegalStateException if not
   * c. Create OrderItem(product, quantity), call order.addItem(item)
   * d. Decrement product stock via productRepository.decrementStock(productId,
   * qty)
   * 4. Save order and return toResponse(saved)
   */
  @Transactional
  public OrderResponse create(OrderCreateRequest request) {
    // TODO: Implement
    return null;
  }

  /**
   * TODO:
   * 1. Load order (throw OrderNotFoundException if not found)
   * 2. Validate status transitions (CANCELLED → anything is not allowed;
   * DELIVERED → CANCELLED is not allowed)
   * 3. Update order status to request.status()
   * 4. Update notes if request.notes() is not null
   * 5. Save and return toResponse(saved)
   */
  @Transactional
  public OrderResponse updateStatus(Long id, OrderUpdateRequest request) {
    // TODO: Implement
    return null;
  }

  /**
   * TODO:
   * 1. Load order (throw OrderNotFoundException if not found)
   * 2. Only PENDING or CONFIRMED orders can be cancelled (throw
   * InvalidOrderStatusTransitionException otherwise)
   * 3. Set status to CANCELLED
   * 4. Restore stock for each item: productRepository.decrementStock(-qty) [or
   * add a restoreStock method]
   * 5. Save order
   */
  @Transactional
  public void cancel(Long id) {
    // TODO: Implement
  }

  // Helper: map Order to OrderResponse (complete, with items)
  private OrderResponse toResponse(Order order) {
    var items = order.getItems().stream()
        .map(i -> new OrderItemResponse(
            i.getProduct().getId(), i.getProduct().getName(),
            i.getQuantity(), i.getUnitPrice(),
            i.getUnitPrice().multiply(java.math.BigDecimal.valueOf(i.getQuantity()))))
        .toList();
    return new OrderResponse(
        order.getId(),
        order.getCustomer().getId(), order.getCustomer().getName(),
        order.getStatus(), order.getTotal(), order.getNotes(),
        items, order.getCreatedAt());
  }

  // Helper: map Order to OrderSummaryResponse (lightweight list view)
  private OrderSummaryResponse toSummary(Order order) {
    return new OrderSummaryResponse(
        order.getId(), order.getCustomer().getName(),
        order.getStatus(), order.getTotal(), order.getCreatedAt());
  }
}
