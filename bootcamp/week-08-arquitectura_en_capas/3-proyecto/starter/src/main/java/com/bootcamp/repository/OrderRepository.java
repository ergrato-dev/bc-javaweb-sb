package com.bootcamp.repository;

import com.bootcamp.domain.Order;
import com.bootcamp.domain.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>,
    JpaSpecificationExecutor<Order> {

  Page<Order> findByCustomer_IdAndStatus(Long customerId, OrderStatus status, Pageable pageable);

  Page<Order> findByCustomer_Id(Long customerId, Pageable pageable);

  @Query("""
      SELECT o FROM Order o
      JOIN FETCH o.customer
      LEFT JOIN FETCH o.items i
      LEFT JOIN FETCH i.product
      WHERE o.id = :id
      """)
  Optional<Order> findByIdWithDetails(@Param("id") Long id);

  @Query("SELECT SUM(o.total) FROM Order o WHERE o.customer.id = :customerId AND o.status = 'DELIVERED'")
  BigDecimal sumDeliveredByCustomer(@Param("customerId") Long customerId);
}
