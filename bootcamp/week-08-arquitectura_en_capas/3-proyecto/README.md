# 🛒 Proyecto Semana 08 — Orders API

## 🎯 Objetivo

Construir una API REST de gestión de órdenes e-commerce aplicando **arquitectura en capas completa**: Controller → Service → Repository con DTOs, MapStruct, Flyway y Swagger.

## 🚀 Ejecutar el proyecto

```bash
./mvnw spring-boot:run
```

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:ordersdb`)

## 📋 Dominio

- **Customer** — clientes registrados
- **Product** — catálogo de productos con stock
- **Order** → OrderItem — pedido con líneas de detalle
- **OrderStatus** — lifecycle: `PENDING → CONFIRMED → SHIPPED → DELIVERED | CANCELLED`

Las migraciones Flyway crean el schema y precargan datos de prueba automáticamente.

## 📝 Tareas a implementar

### 1. `OrderService` — 5 métodos con lógica de negocio

Implementa los TODOs en `OrderService`:

- `findAll(customerId, status, pageable)` — Specification + paginación
- `findById(id)` — load con JOIN FETCH (evita N+1)
- `create(request)` — validar stock, decrementar, calcular total
- `updateStatus(id, request)` — validar transiciones de estado
- `cancel(id)` — solo PENDING/CONFIRMED, restaurar stock

### 2. `OrderController` — 5 endpoints REST

Implementa los TODOs del controller:
- `GET /api/orders` — con customerId, status filter + Pageable
- `GET /api/orders/{id}` — detalles completos con items
- `POST /api/orders` — crear pedido (201 Created)
- `PUT /api/orders/{id}/status` — actualizar estado
- `DELETE /api/orders/{id}` — cancelar (204 No Content)

### 3. ProductService (bonus)

Si tienes tiempo, crea `ProductService` con endpoints para gestión del catálogo.

## ✅ Criterios de evaluación

| Criterio | Pts |
|---|---|
| Arquitectura en capas correcta (Controller no toca Repository) | 20 |
| `OrderService.create()`: validación stock + decremento | 25 |
| `OrderService.updateStatus()`: transiciones de estado válidas | 15 |
| `OrderController`: todos los endpoints con status HTTP correctos | 25 |
| Tests pasan: `OrderServiceTest` (6 tests Mockito) | 15 |

**Total: 100 puntos — Mínimo aprobatorio: 70**

## 🧪 Tests

```bash
./mvnw test
```

Los tests en `OrderServiceTest` usan **Mockito** (sin Spring) — se ejecutan en milisegundos.
