# Práctica 01 — Code Review: Antipatrones Comunes

> Detectar y corregir errores en una API existente es una habilidad clave del
> desarrollador profesional. En esta práctica revisarás código real con errores
> y los corregirás aplicando todo lo aprendido en el bootcamp.

---

## 🎯 Objetivo

Identificar y corregir los 5 antipatrones más frecuentes en APIs Spring Boot:

1. **N+1 Query Problem** — múltiples queries por cada elemento de una lista
2. **Lógica de negocio en el Controller** — violación del principio de separación de capas
3. **Contraseñas en texto plano** — vulnerabilidad de seguridad crítica
4. **Entidad JPA expuesta** — filtración de datos internos
5. **Sin manejo de errores global** — excepciones no controladas

---

## ⚠️ El Código a Revisar

Tienes una API de gestión de órdenes con los archivos en `starter/`. Esta API funciona, pero tiene problemas graves de seguridad, performance y arquitectura.

---

## Paso 1: Analizar el N+1 Problem

Lee `starter/OrderController.java` y encuentra por qué `GET /orders` ejecuta N+1 queries.

**El problema:**

```java
// En OrderController.java — PASO 1
// Este endpoint tiene un N+1 problem.
// Por cada Order, Hibernate ejecuta una query extra para cargar los items.
// Con 100 órdenes = 101 queries. Con 1000 órdenes = 1001 queries.
//
// Descomenta este código SIN N+1 en OrderRepository.java:
// @Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.customerId = :customerId")
// List<Order> findByCustomerIdWithItems(@Param("customerId") Long customerId);
```

**Verifica en los logs de Hibernate** (`spring.jpa.show-sql: true`):
- Antes del fix: verás una query `SELECT` por cada orden
- Después del fix: verás una sola query con `JOIN`

---

## Paso 2: Mover lógica al Service

Lee `starter/OrderController.java` y encuentra el método `createOrder`.

**El problema:**
```java
// ❌ INCORRECTO — lógica de negocio en el controller
@PostMapping
public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest req) {
    if (req.items().isEmpty()) {
        return ResponseEntity.badRequest().build();
    }
    double total = req.items().stream()
            .mapToDouble(item -> item.price() * item.quantity())
            .sum();
    var order = new Order(req.customerId(), req.items(), total, OrderStatus.PENDING);
    return ResponseEntity.ok(orderRepository.save(order));
}
```

**Descomenta en `starter/OrderService.java`** la versión correcta:
```java
// ✅ CORRECTO — controller delega al service
// @PostMapping
// public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest req) {
//     return ResponseEntity.status(201).body(orderService.create(req));
// }
```

---

## Paso 3: Hashear contraseñas con BCrypt

Lee `starter/UserService.java`. Las contraseñas se guardan en texto plano.

**El problema:**

```java
// ❌ CRÍTICO — nunca guardar passwords en texto plano
user.setPassword(request.password());
userRepository.save(user);
```

**Descomenta en `starter/UserService.java`**:
```java
// ✅ Hashear con BCrypt antes de persistir
// user.setPassword(passwordEncoder.encode(request.password()));
// userRepository.save(user);
```

**Para verificar**: inspecciona la BD con H2 Console (`/h2-console`). El password debe verse como `$2a$12$...`.

---

## Paso 4: Usar DTO en lugar de exponer la entidad

Lee `starter/UserController.java`. El endpoint retorna la entidad `User` directamente.

**El problema:**
```java
// ❌ MAL — expone password, ids internos, relaciones lazy, etc.
@GetMapping("/{id}")
public ResponseEntity<User> getUser(@PathVariable Long id) {
    return ResponseEntity.ok(userRepository.findById(id).orElseThrow());
}
```

**Descomenta en `starter/UserController.java`**:
```java
// ✅ BIEN — retorna solo los campos necesarios
// @GetMapping("/{id}")
// public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
//     return ResponseEntity.ok(userService.findById(id));
// }
```

**UserResponse solo tiene:** `id`, `email`, `name`, `createdAt`. Sin `password`.

---

## Paso 5: Agregar GlobalExceptionHandler

Lee `starter/`. No existe ningún `@ControllerAdvice`. Cuando lanzas una excepción en un servicio, la app retorna un stack trace JSON feo con status 500.

**Crea `starter/GlobalExceptionHandler.java`** con este contenido:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("VALIDATION_ERROR", String.join(", ", errors)));
    }

    public record ErrorResponse(String code, String message) {}
}
```

---

## ✅ Verificación Final

Levanta la app con `mvn spring-boot:run` y verifica:

```bash
# 1. Crear usuario (password debe guardarse hasheado)
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"email":"test@example.com","password":"secret123","name":"Test"}'

# 2. Ver usuario (NO debe aparecer el campo password)
curl http://localhost:8080/users/1

# 3. Crear orden vacía — debe retornar 400, no 500
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{"customerId":1,"items":[]}'

# 4. Ver orden — una sola query en logs (no N+1)
curl http://localhost:8080/orders/customer/1
```

---

## 📋 Checklist

- [ ] `GET /orders/customer/{id}` usa `JOIN FETCH` — una sola query en logs
- [ ] `POST /orders` delega toda la lógica al `OrderService`
- [ ] `POST /users` guarda password con BCrypt (`$2a$12$...` en BD)
- [ ] `GET /users/{id}` retorna `UserResponse` sin el campo `password`
- [ ] `POST /orders` con items vacíos retorna `400` con JSON `{"code":"VALIDATION_ERROR",...}`
