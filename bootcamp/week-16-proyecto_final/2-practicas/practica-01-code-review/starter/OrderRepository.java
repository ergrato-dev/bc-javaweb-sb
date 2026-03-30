package com.bootcamp.review.repository;

import com.bootcamp.review.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // ❌ N+1: no carga los items en la misma query
    List<Order> findByCustomerId(Long customerId);

    // ============================================
    // PASO 1: Fix del N+1 — descomenta estas líneas:
    // ============================================
    // ✅ JOIN FETCH: carga Order + items en una sola query SQL
    // @Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.customerId = :customerId")
    // List<Order> findByCustomerIdWithItems(@Param("customerId") Long customerId);
}
