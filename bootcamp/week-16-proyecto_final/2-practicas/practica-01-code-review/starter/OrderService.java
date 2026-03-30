package com.bootcamp.review.service;

import com.bootcamp.review.domain.Order;
import com.bootcamp.review.domain.OrderItem;
import com.bootcamp.review.dto.CreateOrderRequest;
import com.bootcamp.review.dto.OrderResponse;
import com.bootcamp.review.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // ============================================
    // PASO 2: Lógica movida al Service
    // ============================================
    // ✅ El service calcula el total, valida las reglas y persiste la entidad.
    // El controller solo delega aquí.
    //
    // Descomenta este método cuando el controller esté arreglado:
    // @Transactional
    // public OrderResponse create(CreateOrderRequest req) {
    //     if (req.items().isEmpty()) {
    //         throw new IllegalArgumentException("Order must have at least one item");
    //     }
    //     double total = req.items().stream()
    //             .mapToDouble(item -> item.price() * item.quantity())
    //             .sum();
    //     var order = new Order();
    //     order.setCustomerId(req.customerId());
    //     order.setTotal(total);
    //     req.items().forEach(i -> {
    //         var item = new OrderItem(i.productId(), i.price(), i.quantity());
    //         order.addItem(item);
    //     });
    //     var saved = orderRepository.save(order);
    //     return new OrderResponse(saved.getId(), saved.getCustomerId(), saved.getTotal());
    // }
}
