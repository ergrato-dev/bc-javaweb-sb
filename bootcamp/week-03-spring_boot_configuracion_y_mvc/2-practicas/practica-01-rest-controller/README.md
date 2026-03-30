# Práctica 01 — Primer REST Controller

## 🎯 Objetivo
Crear un endpoint REST GET que retorna una lista de productos en JSON.

## ⏱️ Duración estimada: 45 minutos

---

## Paso 1: Record DTO de respuesta

```java
public record ProductResponse(Long id, String name, double price) {}
```

**Descomenta la sección `// STEP 1`** en el starter.

---

## Paso 2: GET /api/products — listar todos

```java
@GetMapping
public List<ProductResponse> getAll() {
    return productService.findAll();
}
```

**Descomenta la sección `// STEP 2`**. Arranca la app y visita `http://localhost:8080/api/products`.

Deberías ver un array JSON con los productos.

---

## Paso 3: GET /api/products/{id} — por ID

```java
@GetMapping("/{id}")
public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
    return productService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}
```

**Descomenta la sección `// STEP 3`**. Prueba:
- `GET /api/products/1` → 200 OK con el producto
- `GET /api/products/999` → 404 Not Found

---

## Paso 4: GET /api/products?category=Electronics — query param

**Descomenta la sección `// STEP 4`**. Prueba:
- `GET /api/products?category=Electronics`

---

## Paso 5: POST /api/products — crear

**Descomenta la sección `// STEP 5`**. Usa curl o Postman:
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Mouse","category":"Electronics","price":29.99}'
```

Deberías recibir `201 Created` con el nuevo producto.

---

## ✅ Verificación Final
- [ ] GET /api/products → 200 + JSON array
- [ ] GET /api/products/1 → 200 + objeto
- [ ] GET /api/products/999 → 404
- [ ] GET /api/products?category=Electronics → 200 + filtrado
- [ ] POST /api/products → 201 + Location header
