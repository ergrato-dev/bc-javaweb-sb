# Proyecto Semana 06 — API de E-commerce con Órdenes y Productos

## 🎯 Descripción

Construye una API de e-commerce con entidades relacionadas: `Customer`, `Order`, `OrderItem` y `Product`. Modela las relaciones JPA correctamente y asegura que no haya N+1.

## 📋 Escenario

Una tienda online necesita gestionar pedidos. Un cliente puede tener múltiples órdenes; cada orden tiene múltiples ítems, cada ítem referencia un producto.

## 🏗️ Modelo de Datos

```
Customer (1) ←──────→ (N) Order
                              ↓
                        OrderItem (N) ←→ (1) Product
```

```java
// Customer → orders: @OneToMany, LAZY
// Order → customer: @ManyToOne
// Order → items: @OneToMany(cascade = ALL, orphanRemoval = true), LAZY
// OrderItem → order: @ManyToOne
// OrderItem → product: @ManyToOne, LAZY
// Product ← (sin relación inversa a OrderItem, no necesaria)
```

## 📌 Requerimientos

### Entidades y Relaciones
- [ ] **R1:** `Customer` con `@OneToMany(mappedBy="customer", fetch=LAZY)` a `Order`
- [ ] **R2:** `Order` con `@ManyToOne` a `Customer` y `@OneToMany(cascade=ALL)` a `OrderItem`
- [ ] **R3:** `OrderItem` como tabla intermedia entre `Order` y `Product` con `quantity` y `unitPrice`
- [ ] **R4:** `Product` como entidad independiente (sin `@OneToMany` a `OrderItem`)

### API
- [ ] **R5:** `POST /orders` — crear orden con lista de ítems; validar stock disponible
- [ ] **R6:** `GET /orders/{id}` — obtener orden con ítems y productos (sin N+1)
- [ ] **R7:** `GET /customers/{id}/orders` — todas las órdenes de un cliente
- [ ] **R8:** `@Transactional` en `createOrder()`: si falla cualquier paso, rollback completo

### Optimización
- [ ] **R9:** `spring.jpa.show-sql=true` en dev; logs demuestran ausencia de N+1 en `GET /orders/{id}`
- [ ] **R10:** `@EntityGraph` o `JOIN FETCH` para cargar orden con ítems en 1-2 queries

## ✅ Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| Relaciones JPA correctamente modeladas | 30 |
| CRUD de órdenes con ítems (cascada) | 25 |
| Sin N+1 demostrado en logs SQL | 20 |
| `@Transactional` con rollback | 15 |
| Validación de stock antes de confirmar orden | 10 |
| **Total** | **100** |
