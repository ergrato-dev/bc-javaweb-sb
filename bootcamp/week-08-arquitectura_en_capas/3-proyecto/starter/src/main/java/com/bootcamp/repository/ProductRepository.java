package com.bootcamp.repository;

import com.bootcamp.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Page<Product> findByCategory(String category, Pageable pageable);

  Page<Product> findByActiveTrueAndStockGreaterThan(int minStock, Pageable pageable);

  @Modifying
  @Query("UPDATE Product p SET p.stock = p.stock - :qty WHERE p.id = :id AND p.stock >= :qty")
  int decrementStock(@Param("id") Long id, @Param("qty") int qty);
}
