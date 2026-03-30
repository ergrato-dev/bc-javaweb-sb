package com.bootcamp.inventory.repository;

import com.bootcamp.inventory.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findBySku(String sku);

  boolean existsBySku(String sku);

  Page<Product> findByCategory(String category, Pageable pageable);

  // Custom JPQL — compatible with real PostgreSQL and testable with
  // Testcontainers
  @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
  Page<Product> searchByName(@Param("name") String name, Pageable pageable);
}
