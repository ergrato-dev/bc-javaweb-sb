# Spring Data JPA — Consultas con JPQL y Paginación

## 🎯 Objetivos
- Escribir consultas custom con `@Query` y JPQL
- Implementar paginación y ordenamiento con `Pageable`

---

## 1. Limitaciones de Query Methods

Los métodos por nombre funcionan para casos simples. Para consultas más complejas se usa `@Query`:

```java
// ⚠️ Método demasiado largo — ilegible
List<Product> findByCategoryAndPriceLessThanAndStockGreaterThanOrderByPriceAsc(
    String category, BigDecimal maxPrice, int minStock);

// ✅ Con @Query — más legible
@Query("SELECT p FROM Product p WHERE p.category = :category " +
       "AND p.price < :maxPrice AND p.stock > :minStock " +
       "ORDER BY p.price ASC")
List<Product> findAvailableByCategory(@Param("category") String category,
                                       @Param("maxPrice") BigDecimal maxPrice,
                                       @Param("minStock") int minStock);
```

---

## 2. JPQL vs SQL Nativo

**JPQL** (Java Persistence Query Language) opera sobre entidades, no tablas:

```java
// JPQL — usa nombre de clase e campos Java
@Query("SELECT p FROM Product p WHERE p.price < :maxPrice")
List<Product> findCheaperThan(@Param("maxPrice") BigDecimal maxPrice);

// SQL nativo — usa nombres de tabla y columnas reales
@Query(value = "SELECT * FROM products WHERE price < :maxPrice", nativeQuery = true)
List<Product> findCheaperThanNative(@Param("maxPrice") BigDecimal maxPrice);
```

> Preferir JPQL — es independiente del motor de base de datos.

---

## 3. Paginación con Pageable

```java
// Repository — agrega Pageable como último parámetro
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategory(String category, Pageable pageable);
    Page<Product> findAll(Pageable pageable);
}
```

```java
// Controller — recibe parámetros de query estándar: ?page=0&size=10&sort=price,asc
@GetMapping
public Page<ProductResponse> getAll(
        @RequestParam(required = false) String category,
        Pageable pageable) {
    return productService.findAll(category, pageable);
}

// Service
public Page<Product> findAll(String category, Pageable pageable) {
    if (category != null) {
        return productRepository.findByCategory(category, pageable);
    }
    return productRepository.findAll(pageable);
}
```

**Llamada paginada:**
```
GET /api/products?page=0&size=5&sort=price,asc
GET /api/products?category=Electronics&page=1&size=10
```

**Respuesta `Page<T>`:**
```json
{
  "content": [...],
  "pageable": { "pageNumber": 0, "pageSize": 5 },
  "totalElements": 42,
  "totalPages": 9,
  "last": false
}
```

---

## 4. Projections — Solo los campos que necesitas

```java
// Interfaz projection — JPA genera la implementación
public interface ProductSummary {
    Long getId();
    String getName();
    BigDecimal getPrice();
}

// Repository retorna projection
List<ProductSummary> findProjectedByCategory(String category);
```

> Más eficiente que cargar la entidad completa cuando solo necesitas 2-3 campos.

---

## 5. Queries de Actualización con @Modifying

```java
@Modifying
@Transactional
@Query("UPDATE Product p SET p.stock = p.stock - :quantity WHERE p.id = :id AND p.stock >= :quantity")
int decrementStock(@Param("id") Long id, @Param("quantity") int quantity);
```

- `@Modifying` es obligatorio para UPDATE/DELETE
- `@Transactional` requerido en queries de escritura
- Retorna `int` (número de filas afectadas)

---

## ✅ Checklist de Verificación
- [ ] `@Query` con JPQL para consultas complejas
- [ ] `@Param` en parámetros nombrados `:param`
- [ ] `Page<T>` en lugar de `List<T>` para paginación
- [ ] `Pageable` como parámetro en controller y service
- [ ] `@Modifying` + `@Transactional` en UPDATE/DELETE
