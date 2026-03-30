# Integración Completa: Controller → Service → Repository

## 🎯 Objetivos
- Conectar todas las capas en un flujo completo
- Identificar los puntos de corte correctos entre capas
- Entender el flujo de una petición HTTP de punta a punta

---

## 1. Flujo de una petición HTTP

```
Cliente HTTP
    │
    ▼ POST /api/orders  { "customerId": 1, "items": [...] }
┌───────────────────┐
│  OrderController  │  1. Deserializa JSON → OrderCreateRequest
│  @RestController  │  2. Valida con @Valid
│  @RequestMapping  │  3. Llama orderService.create(request)
└────────┬──────────┘
         │
         ▼
┌────────────────────┐
│   OrderService     │  4. Busca Customer en CustomerRepository
│   @Service         │  5. Valida reglas de negocio
│   @Transactional   │  6. Crea Order con OrderItems
└────────┬───────────┘  7. Guarda con OrderRepository
         │              8. Mapea a OrderResponse
         ▼
┌────────────────────┐
│  OrderRepository   │  9. Ejecuta INSERT en BD (Hibernate)
│  JpaRepository     │
└────────────────────┘
         │
         ▼ retorna Order guardado
    back to Service
    back to Controller
    │
    ▼ HTTP 201 Created  { "id": 42, "total": 150.00, ... }
Cliente HTTP
```

---

## 2. Estructura de paquetes completa

```
com.bootcamp/
├── controller/
│   ├── OrderController.java         @RestController
│   └── CustomerController.java
├── service/
│   ├── OrderService.java            @Service @Transactional
│   └── CustomerService.java
├── repository/
│   ├── OrderRepository.java         JpaRepository + JpaSpecificationExecutor
│   ├── CustomerRepository.java
│   └── ProductRepository.java
├── domain/
│   ├── Order.java                   @Entity
│   ├── OrderItem.java               @Entity
│   ├── Customer.java                @Entity
│   └── OrderStatus.java             enum
├── dto/
│   ├── OrderCreateRequest.java      record + @Valid
│   ├── OrderResponse.java           record
│   ├── OrderSummaryResponse.java    record (listados)
│   ├── OrderItemRequest.java        record
│   └── OrderUpdateRequest.java      record
└── exception/
    ├── OrderNotFoundException.java
    ├── CustomerNotFoundException.java
    └── GlobalExceptionHandler.java  @RestControllerAdvice
```

---

## 3. Ejemplo integrado — Order completo

### Flujo Create Order

```java
// 1. Controller
@PostMapping
public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderCreateRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
                         .body(orderService.create(request));
}

// 2. Service
@Transactional
public OrderResponse create(OrderCreateRequest request) {
    var customer = customerRepository.findById(request.customerId())
        .orElseThrow(() -> new CustomerNotFoundException(request.customerId()));

    var order = new Order(customer);  // status = PENDING by default

    request.items().forEach(itemReq -> {
        var product = productRepository.findById(itemReq.productId())
            .orElseThrow(() -> new ProductNotFoundException(itemReq.productId()));
        order.addItem(new OrderItem(product, itemReq.quantity(), product.getPrice()));
    });

    return toResponse(orderRepository.save(order));
}

// 3. Repository — no custom methods needed here (save inherited from JpaRepository)
```

### Flujo Get Orders con filtros

```java
// 1. Controller
@GetMapping
public ResponseEntity<Page<OrderSummaryResponse>> findAll(
        @RequestParam(required = false) Long customerId,
        @RequestParam(required = false) OrderStatus status,
        Pageable pageable) {
    return ResponseEntity.ok(orderService.findAll(customerId, status, pageable));
}

// 2. Service
@Transactional(readOnly = true)
public Page<OrderSummaryResponse> findAll(Long customerId, OrderStatus status, Pageable pageable) {
    Specification<Order> spec = Specification.where(null);
    if (customerId != null) spec = spec.and((r, q, cb) -> cb.equal(r.get("customer").get("id"), customerId));
    if (status != null)     spec = spec.and((r, q, cb) -> cb.equal(r.get("status"), status));
    return orderRepository.findAll(spec, pageable).map(this::toSummary);
}
```

---

## 4. Testing por capa

Cada capa se testea con su slice correspondiente:

```java
// Controller → @WebMvcTest (no carga JPA)
@WebMvcTest(OrderController.class)
class OrderControllerTest { ... }

// Service → @ExtendWith(MockitoExtension.class) (sin Spring)
@ExtendWith(MockitoExtension.class)
class OrderServiceTest { ... }

// Repository → @DataJpaTest (H2 + JPA)
@DataJpaTest
class OrderRepositoryTest { ... }
```

Esta separación hace que los tests sean rápidos y localizados.

---

## ✅ Checklist

- [ ] Paquetes separados: controller / service / repository / domain / dto / exception
- [ ] Cada petición fluye: Controller → Service → Repository
- [ ] Controller NUNCA accede a Repository directamente
- [ ] Service NUNCA retorna entidades JPA
- [ ] Tests segmentados por capa (no solo `@SpringBootTest`)
