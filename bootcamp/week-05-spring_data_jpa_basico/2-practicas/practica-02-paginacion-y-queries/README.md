# Práctica 02 — Paginación y @Query

## 🎯 Objetivo
Agregar paginación a un endpoint y escribir consultas JPQL con `@Query`.

## ⏱️ Duración estimada: 45 minutos

---

## Paso 1: Explorar la API base

**Abre `starter/PaginationApp.java`** — tiene un endpoint `GET /api/products` que retorna todos los productos sin paginación.

Prueba: `GET /api/products` → retorna todos los productos sin filtro.

---

## Paso 2: Agregar Pageable al endpoint

**Descomenta la sección `// STEP 2`** — modifica el endpoint para recibir `Pageable`.

Prueba con paginación:
```
GET /api/products?page=0&size=2
GET /api/products?page=1&size=2
```

---

## Paso 3: Agregar @Query para búsqueda por nombre

**Descomenta la sección `// STEP 3`** — agrega `@Query` con LIKE para búsqueda parcial.

Prueba: `GET /api/products/search?name=lap`

---

## Paso 4: Filtrar por precio máximo con @Query

**Descomenta la sección `// STEP 4`** — agrega `@Query` para filtrar precio.

Prueba: `GET /api/products?maxPrice=100`

---

## Paso 5: Ordenamiento

**Descomenta la sección `// STEP 5`** — explora los parámetros de sort.

Prueba:
```
GET /api/products?sort=price,asc
GET /api/products?sort=name,desc
```

---

## ✅ Verificación Final
- [ ] Respuesta paginada con `totalElements`, `totalPages`, `content`
- [ ] Paginación funcional (`?page=0&size=2`)
- [ ] Búsqueda por nombre parcial (`?name=lap`)
- [ ] Filtro por precio máximo
- [ ] Ordenamiento por cualquier campo
