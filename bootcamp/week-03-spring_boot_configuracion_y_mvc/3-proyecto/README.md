# Proyecto Semana 03 — API REST de Catálogo de Productos

## 🎯 Descripción

Construye una API REST funcional para gestión de un catálogo de productos. Esta semana el almacenamiento es en memoria (sin BD todavía); el foco está en los endpoints REST, códigos HTTP correctos y la configuración de Spring Boot.

## 📋 Escenario

Una tienda online necesita una API para gestionar su catálogo de productos. La API debe ser consumible desde cualquier cliente HTTP (Postman, curl, frontend React).

## 🏗️ Modelo

```java
// DTOs (en el starter)
public record ProductRequest(String name, String category, double price, int stock) {}

public record ProductResponse(Long id, String name, String category, double price, int stock, boolean inStock) {}
```

## 📌 Endpoints Requeridos

| Método | Path | Descripción | HTTP Response |
|--------|------|-------------|---------------|
| `GET` | `/api/products` | Listar todos los productos | `200 OK` |
| `GET` | `/api/products/{id}` | Obtener producto por ID | `200 OK` / `404 Not Found` |
| `GET` | `/api/products?category=Electronics` | Filtrar por categoría | `200 OK` |
| `POST` | `/api/products` | Crear producto | `201 Created` con Location header |
| `PUT` | `/api/products/{id}` | Actualizar producto completo | `200 OK` / `404 Not Found` |
| `DELETE` | `/api/products/{id}` | Eliminar producto | `204 No Content` / `404 Not Found` |

## 📌 Requerimientos Técnicos

- [ ] `ResponseEntity<?>` usado en todos los endpoints (no retornar objetos directamente)
- [ ] `@RestControllerAdvice` con manejo de `ProductNotFoundException` → 404
- [ ] `@ExceptionHandler` para `IllegalArgumentException` → 400
- [ ] Actuator habilitado: `GET /actuator/health` → `{"status":"UP"}`
- [ ] Almacenamiento en `ArrayList` con `AtomicLong` para IDs (en memoria)
- [ ] Validación en `ProductService`: nombre no vacío, precio > 0

## 📂 Estructura del Starter

```
src/main/java/com/bootcamp/
├── ProductsApiApplication.java
├── controller/
│   └── ProductController.java        ← TODOs aquí
├── service/
│   └── ProductService.java           ← TODOs aquí
├── dto/
│   ├── ProductRequest.java
│   └── ProductResponse.java
└── exception/
    ├── ProductNotFoundException.java
    └── GlobalExceptionHandler.java   ← TODOs aquí
```

## ✅ Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| Todos los endpoints funcionando | 40 |
| Códigos HTTP semánticamente correctos | 20 |
| `@RestControllerAdvice` con 404 y 400 | 15 |
| `application.yml` con Actuator | 10 |
| Actuator `/actuator/health` accesible | 5 |
| Location header en POST 201 | 10 |
| **Total** | **100** |
