# Rúbrica de Evaluación — Semana 05: Spring Data JPA Básico

---

## 📊 Distribución de Evidencias

| Tipo de Evidencia | Porcentaje |
|-------------------|-----------|
| 🧠 Conocimiento | 30% |
| 💪 Desempeño | 40% |
| 📦 Producto | 30% |

**Calificación mínima aprobatoria por evidencia: 70%**

---

## 🧠 Conocimiento (30%) — Evaluación Teórica

### Preguntas Tipo Quiz

| Concepto | Indicador de Logro |
|----------|--------------------|
| `@Entity` y `@Table` | Explica qué hace cada anotación y cuándo usar `@Table` |
| `@Id` y `@GeneratedValue` | Distingue entre estrategias: IDENTITY, SEQUENCE, AUTO |
| `JpaRepository<T, ID>` | Enumera al menos 5 métodos heredados y su equivalente SQL |
| Derived Query Methods | Deduce el SQL que genera un nombre de método dado |
| `@Query` JPQL vs SQL nativo | Explica diferencias y cuándo usar cada uno |
| `Pageable` y `Page<T>` | Describe los campos de una respuesta `Page<T>` |
| `@DataJpaTest` | Explica qué carga y qué NO carga este slice |
| `ddl-auto` | Diferencia entre `create-drop` (dev) y `validate` (prod) |

**Escala:**
- 90–100%: Domina todos los conceptos con ejemplos precisos
- 75–89%: Comprende la mayoría; confunde algún detalle menor
- 70–74%: Comprende lo esencial pero con lagunas en casos avanzados
- < 70%: No aprobado — revisar teoría

---

## 💪 Desempeño (40%) — Prácticas en Sesión

### Práctica 01 — Mi Primera Entidad JPA

| Criterio | Puntos |
|----------|--------|
| Entidad `Product` con todas las anotaciones JPA correctas | 25 pts |
| `protected Product()` constructor vacío presente | 15 pts |
| `ProductRepository` extiende `JpaRepository<Product, Long>` | 20 pts |
| Derived query methods `findByCategory` y `existsByName` funcionan | 25 pts |
| Tabla `products` visible en H2 Console y datos iniciales presentes | 15 pts |
| **Total** | **100 pts** |

### Práctica 02 — Paginación y @Query

| Criterio | Puntos |
|----------|--------|
| Endpoint retorna `Page<Prod>` con `Pageable` | 25 pts |
| `?page=0&size=2` devuelve 2 elementos correctos | 15 pts |
| `@Query` LIKE para búsqueda parcial funciona | 20 pts |
| Filtro por precio máximo con `@Query` funciona | 20 pts |
| Respuesta incluye `totalElements`, `totalPages`, `content` | 20 pts |
| **Total** | **100 pts** |

**Criterio de Desempeño Combinado:** promedio de ambas prácticas ≥ 70 pts.

---

## 📦 Producto (30%) — Proyecto Integrador

### API de Tienda Online con Spring Data JPA

| Criterio | Puntos | Descripción |
|----------|--------|-------------|
| **Repository — métodos custom** | 30 pts | Los 4 métodos implementados: `findByCategory`, `searchByName`, `findByPriceBetween`, `decrementStock` |
| **Tests pasando** | 25 pts | Los 7 tests de `ProductRepositoryTest` en verde con `./mvnw test` |
| **Service — lógica completa** | 25 pts | Los 7 métodos del servicio implementados correctamente |
| **Controller — endpoints REST** | 20 pts | Los 7 endpoints con códigos HTTP correctos (201, 204, 404) |
| **Total** | **100 pts** | |

### Detalle por Criterio

#### Repository (30 pts)
- `findByCategory(String, Pageable)` → Page<Product> — 7 pts
- `searchByName(@Param String)` con JPQL LIKE — 8 pts
- `findByPriceBetween(@Param min, @Param max)` — 7 pts
- `decrementStock(@Param id, @Param qty)` con `@Modifying` — 8 pts

#### Tests (25 pts)
- `findByCategory_shouldReturnOnlyMatchingProducts` — 4 pts
- `findByCategory_shouldSupportPagination` — 3 pts
- `searchByName_shouldFindPartialCaseInsensitiveMatch` — 4 pts
- `findByPriceBetween_shouldReturnProductsInRange` — 4 pts
- `decrementStock_shouldUpdateStockWhenSufficient` — 5 pts
- `decrementStock_shouldNotUpdateWhenInsufficientStock` — 3 pts
- `existsByName` tests — 2 pts

#### Service (25 pts)
- `findAll` con paginación y filtro opcional — 5 pts
- `findById` lanza `ProductNotFoundException` cuando no existe — 4 pts
- `create` guarda y retorna `ProductResponse` — 4 pts
- `update` actualiza todos los campos — 4 pts
- `delete` verifica existencia antes de eliminar — 4 pts
- `decrementStock` maneja stock insuficiente — 4 pts

#### Controller (20 pts)
- `GET /api/products` con paginación — 3 pts
- `GET /api/products/search?name=` — 3 pts
- `GET /api/products/{id}` retorna 404 correcto — 3 pts
- `POST /api/products` retorna 201 + Location header — 4 pts
- `PUT /api/products/{id}` — 3 pts
- `DELETE /api/products/{id}` retorna 204 — 4 pts

---

## 🎓 Calificación Final

| Evidencia | Peso | Puntos Máx |
|-----------|------|-----------|
| Conocimiento | 30% | 100 pts |
| Desempeño | 40% | 100 pts |
| Producto | 30% | 100 pts |

**Fórmula:** `(Conocimiento × 0.30) + (Desempeño × 0.40) + (Producto × 0.30)`

**Ejemplo:** 85 × 0.30 + 90 × 0.40 + 80 × 0.30 = 25.5 + 36 + 24 = **85.5 pts**

---

## 📌 Notas para el Instructor

- `target/` debe estar en `.gitignore` — si el estudiante incluye `.class` en el commit, descontar 5 pts del Producto.
- Los tests de repositorio son la medida más confiable del aprendizaje — priorizarlos como criterio de revisión.
- Verificar que el constructor `protected Product()` esté presente — es un error conceptual grave si falta.
- Para el proxy de evaluación rápida: ejecutar `./mvnw test` en el starter del estudiante y contar tests en verde.
