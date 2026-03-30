package com.bootcamp.service;

import com.bootcamp.domain.*;
import com.bootcamp.dto.*;
import com.bootcamp.exception.*;
import com.bootcamp.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock OrderRepository orderRepository;
    @Mock CustomerRepository customerRepository;
    @Mock ProductRepository productRepository;

    @InjectMocks OrderService orderService;

    @Test
    void create_shouldThrowCustomerNotFoundException_whenCustomerDoesNotExist() {
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());

        var request = new OrderCreateRequest(99L, List.of(new OrderItemRequest(1L, 2)), null);

        assertThatThrownBy(() -> orderService.create(request))
            .isInstanceOf(CustomerNotFoundException.class)
            .hasMessageContaining("99");
    }

    @Test
    void create_shouldThrowProductNotFoundException_whenProductDoesNotExist() {
        var customer = new Customer("Alice", "alice@example.com", null);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        var request = new OrderCreateRequest(1L, List.of(new OrderItemRequest(99L, 1)), null);

        assertThatThrownBy(() -> orderService.create(request))
            .isInstanceOf(ProductNotFoundException.class)
            .hasMessageContaining("99");
    }

    @Test
    void create_shouldThrowIllegalStateException_whenProductHasInsufficientStock() {
        var customer = new Customer("Alice", "alice@example.com", null);
        var product = new Product("Laptop", "desc", BigDecimal.valueOf(1000), 0, "Electronics");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        var request = new OrderCreateRequest(1L, List.of(new OrderItemRequest(1L, 5)), null);

        assertThatThrownBy(() -> orderService.create(request))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void findById_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        when(orderRepository.findByIdWithDetails(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.findById(99L))
            .isInstanceOf(OrderNotFoundException.class)
            .hasMessageContaining("99");
    }

    @Test
    void cancel_shouldThrowInvalidTransition_whenOrderIsDelivered() {
        var customer = new Customer("Bob", "bob@example.com", null);
        var order = new Order(customer, null);
        order.setStatus(OrderStatus.DELIVERED);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        assertThatThrownBy(() -> orderService.cancel(1L))
            .isInstanceOf(InvalidOrderStatusTransitionException.class);
    }

    @Test
    void updateStatus_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        when(orderRepository.findById(99L)).thenReturn(Optional.empty());

        var request = new OrderUpdateRequest(OrderStatus.CONFIRMED, null);

        assertThatThrownBy(() -> orderService.updateStatus(99L, request))
            .isInstanceOf(OrderNotFoundException.class);
    }
}
