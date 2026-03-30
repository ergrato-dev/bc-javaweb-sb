package com.bootcamp.repository;

import com.bootcamp.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

  /**
   * TODO:
   * Add a Derived Query Method to find products by category (case-sensitive).
   * Method name: findByCategory(String category, Pageable pageable)
   * Returns: Page<Product>
   */

  /**
   * TODO:
   * Add a @Query method to search products by name (case-insensitive, partial
   * match).
   * JPQL: SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%',
   * :name, '%'))
   * Returns: List<Product>
   */

  /**
   * TODO:
   * Add a @Query method to find products with price between minPrice and
   * maxPrice.
   * JPQL: SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max
   * Returns: List<Product>
   */

  /**
   * TODO:
   * Add a @Modifying + @Transactional @Query to decrement stock by quantity.
   * Condition: only if stock >= quantity
   * JPQL: UPDATE Product p SET p.stock = p.stock - :qty WHERE p.id = :id AND
   * p.stock >= :qty
   * Returns: int (number of rows updated)
   */

  boolean existsByName(String name);
}
