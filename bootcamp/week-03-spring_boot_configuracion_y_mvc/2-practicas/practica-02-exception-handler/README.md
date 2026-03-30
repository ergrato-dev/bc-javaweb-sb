# Práctica 02 — Manejo de Excepciones Global

## 🎯 Objetivo
Implementar un `@RestControllerAdvice` que centralice el manejo de errores.

## ⏱️ Duración estimada: 45 minutos

---

## Paso 1: Excepción personalizada

```java
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Long id) {
        super(resourceName + " not found with id: " + id);
    }
}
```

**Descomenta la sección `// STEP 1`**.

---

## Paso 2: Record de respuesta de error

```java
record ErrorResponse(int status, String error, String message, String path) {}
```

**Descomenta la sección `// STEP 2`**.

---

## Paso 3: @RestControllerAdvice

**Descomenta la sección `// STEP 3`**. Este handler captura `ResourceNotFoundException` y retorna 404.

---

## Paso 4: Lanzar la excepción desde el Service

```java
public ProductResponse findById(Long id) {
    return store.stream()
            .filter(p -> p.id().equals(id))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Product", id));
}
```

**Descomenta la sección `// STEP 4`**.

---

## Paso 5: Verificar respuesta de error

```bash
curl http://localhost:8080/api/products/999
```

Respuesta esperada:
```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Product not found with id: 999",
  "path": "/api/products/999"
}
```

**Descomenta la sección `// STEP 5`** — handler para `IllegalArgumentException` con 400.

---

## ✅ Verificación Final
- [ ] /api/products/999 → 404 con JSON de error
- [ ] /api/products/-1 → 400 con JSON de error
- [ ] /api/products/1 → 200 con el producto
