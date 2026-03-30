# Service Layer y Repository Pattern

## 🎯 Objetivos
- Dominar el patrón Service Layer
- Aplicar buenas prácticas de diseño de servicios
- Entender el Repository Pattern en Spring Data

---

## 1. Service Layer — Principios

### Interfaz vs Clase concreta

Spring Boot no requiere interfaces para servicios — son útiles solo cuando hay múltiples implementaciones (prod vs mock):

```java
// ✅ Clase directa (suficiente para la mayoría de casos)
@Service
public class OrderService { ... }

// ✅ Con interfaz cuando existe más de una implementación
public interface OrderService { ... }

@Service
@Primary
public class OrderServiceImpl implements OrderService { ... }

@Service
@Profile("test")
public class OrderServiceStub implements OrderService { ... }
```

### Un servicio por dominio

```
OrderService     → gestión de pedidos
CustomerService  → gestión de clientes
ProductService   → gestión de productos
```

Evitar un `AppService` que haga todo — viola el Principio de Responsabilidad Única.

---

## 2. Manejo de errores en el Service

El Service es quien lanza las excepciones de negocio. El Controller NUNCA accede a repositories directamente para verificar existencia.

```java
@Service
public class OrderService {

    @Transactional
    public OrderResponse cancel(Long id, String reason) {
        // 1. Obtener recurso — lanza excepción si no existe
        var order = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));

        // 2. Validar regla de negocio
        if (order.getStatus() == OrderStatus.SHIPPED) {
            throw new OrderCannotBeCancelledException(
                "Cannot cancel a shipped order");
        }

        // 3. Aplicar cambio de estado
        order.cancel(reason);

        return toResponse(orderRepository.save(order));
    }
}
```

---

## 3. Queries avanzadas en Repository

### Derived Query Methods — casos comunes

```java
// Paginación + filtro
Page<Order> findByStatus(OrderStatus status, Pageable pageable);

// Filtro por relación (usando _ para navegar)
Page<Order> findByCustomer_IdAndStatus(Long customerId, OrderStatus status, Pageable pageable);

// Rangos de fechas
List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

// Exists
boolean existsByCustomer_IdAndStatus(Long customerId, OrderStatus status);
```

### Aggregation en Service (cálculo en memoria vs DB)

```java
// ✅ Bien — dejar que la BD agregue
@Query("SELECT SUM(o.total) FROM Order o WHERE o.customer.id = :id AND o.status = 'COMPLETED'")
BigDecimal sumCompletedByCustomer(@Param("id") Long customerId);

// ❌ Mal — traer todo a memoria para agregar
orderRepository.findAll().stream()
    .filter(o -> o.getCustomer().getId().equals(id))
    .mapToDouble(o -> o.getTotal().doubleValue()).sum();
```

---

## 4. Mapeo DTO ↔ Entidad

Dos opciones oficiales:

### a) Mapeo manual (simple y explícito)

```java
private OrderResponse toResponse(Order order) {
    return new OrderResponse(
        order.getId(),
        order.getCustomer().getName(),
        order.getTotal(),
        order.getStatus(),
        order.getCreatedAt()
    );
}
```

### b) MapStruct (semana 04 — elimina boilerplate)

```java
@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "customer.name", target = "customerName")
    OrderResponse toResponse(Order order);

    Order toEntity(OrderCreateRequest request);
}
```

---

## 5. `@Transactional` — reglas prácticas

```java
@Service
@Transactional(readOnly = true)  // lectura por defecto para toda la clase
public class OrderService {

    // override individual para escritura
    @Transactional
    public OrderResponse create(OrderCreateRequest request) { ... }

    @Transactional
    public void delete(Long id) { ... }

    // hereda readOnly = true de la clase
    public Page<OrderResponse> findAll(Pageable pageable) { ... }
    public OrderResponse findById(Long id) { ... }
}
```

> **Regla:** `readOnly = true` optimiza lecturas (Hibernate no rastrea cambios). Siempre en métodos de consulta.

---

## ✅ Checklist

- [ ] Un Service por dominio — sin "AppService" genérico
- [ ] Excepciones de negocio lanzadas SOLO en el Service
- [ ] Aggregation delegada a la BD (no en memoria)
- [ ] `@Transactional(readOnly=true)` por defecto en la clase
- [ ] Mapeo consistente: entidad → DTO siempre en el Service
