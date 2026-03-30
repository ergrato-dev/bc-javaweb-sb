# Arquitectura en Capas — Teoría y Aplicación

## 🎯 Objetivos
- Comprender la arquitectura en capas y sus responsabilidades
- Separar correctamente Controller, Service y Repository
- Aplicar el patrón Service Layer para encapsular lógica de negocio
- Usar DTOs para aislar la API del modelo de dominio

---

## 1. ¿Por qué arquitectura en capas?

Sin arquitectura, el código se mezcla: lógica de negocio en controladores, queries en todas partes, entidades expuestas directamente. La arquitectura en capas asigna **una responsabilidad por capa**.

```
┌────────────────────────────┐
│   Controller (API Layer)   │  Recibe HTTP, valida, delega
├────────────────────────────┤
│  Service (Business Layer)  │  Lógica de negocio, orquestación
├────────────────────────────┤
│ Repository (Data Layer)    │  Acceso a datos, queries JPA
├────────────────────────────┤
│   Domain (Entities)        │  Modelo de datos JPA
└────────────────────────────┘
```

---

## 2. Capa Controller

**Responsabilidades:**
- Mapear HTTP endpoints (`@GetMapping`, `@PostMapping`, etc.)
- Recibir y validar input (`@Valid`, `@RequestBody`, `@PathVariable`)
- Delegar TODA la lógica al Service
- Transformar el resultado a ResponseEntity con el código HTTP correcto

```java
// ✅ Controller delgado — solo HTTP + delegación
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderCreateRequest request) {
        // SOLO delegar — sin lógica de negocio aquí
        OrderResponse created = orderService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
```

❌ **Anti-patrones en Controller:**
```java
// ❌ INCORRECTO — lógica de negocio en el controller
@PostMapping
public ResponseEntity<Order> create(@RequestBody Order order) {
    if (order.getTotal().compareTo(BigDecimal.ZERO) <= 0) {
        throw new IllegalArgumentException("Total must be positive");
    }
    order.setStatus(OrderStatus.PENDING);  // ← lógica de negocio
    return ResponseEntity.ok(orderRepository.save(order)); // ← acceso directo a repo
}
```

---

## 3. Capa Service

**Responsabilidades:**
- Contener TODA la lógica de negocio
- Orquestar múltiples repositories si es necesario
- Gestionar transacciones (`@Transactional`)
- Mapear entre DTOs y entidades

```java
@Service
@Transactional(readOnly = true)  // optimización: lectura por defecto
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    // Constructor injection (obligatorio para testabilidad)
    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional  // override: escritura necesita transacción
    public OrderResponse create(OrderCreateRequest request) {
        // Lógica de negocio aquí
        var customer = customerRepository.findById(request.customerId())
            .orElseThrow(() -> new CustomerNotFoundException(request.customerId()));

        var order = new Order(customer, OrderStatus.PENDING);
        request.items().forEach(item -> order.addItem(new OrderItem(item)));

        return toResponse(orderRepository.save(order));
    }
}
```

---

## 4. Capa Repository

**Responsabilidades:**
- Proveer acceso a datos sin lógica de negocio
- Definir queries JPQL o nativas cuando las derived no alcanzan
- Retornar entidades, Projections o datos agregados

```java
public interface OrderRepository extends JpaRepository<Order, Long>,
                                          JpaSpecificationExecutor<Order> {

    Page<Order> findByCustomer_IdAndStatus(Long customerId, OrderStatus status, Pageable pageable);

    @Query("""
           SELECT o FROM Order o
           JOIN FETCH o.customer
           LEFT JOIN FETCH o.items
           WHERE o.id = :id
           """)
    Optional<Order> findByIdWithDetails(@Param("id") Long id);
}
```

---

## 5. DTOs — separar API del dominio

Los DTOs son **contrato con el cliente HTTP**, las entidades son **contrato con la base de datos**. Nunca deben ser lo mismo.

| Escenario | Usar |
|---|---|
| Respuesta al cliente | `OrderResponse` record |
| Crear recurso | `OrderCreateRequest` record |
| Actualizar recurso | `OrderUpdateRequest` record |
| Listado paginado | `OrderSummaryResponse` record |

```java
// Request — datos que el cliente envía
public record OrderCreateRequest(
    @NotNull Long customerId,
    @NotEmpty List<OrderItemRequest> items
) {}

// Response — datos que la API retorna (nunca la entidad directa)
public record OrderResponse(
    Long id, String customerName, BigDecimal total, OrderStatus status
) {}
```

---

## ✅ Checklist

- [ ] Controllers únicamente reciben HTTP y delegan al Service
- [ ] Services contienen toda la lógica de negocio
- [ ] Repositories solo acceden a datos
- [ ] Entidades JPA NUNCA expuestas directamente en la API
- [ ] Inyección por constructor en todas las capas
- [ ] `@Transactional(readOnly=true)` en servicios de lectura
