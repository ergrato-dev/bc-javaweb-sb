# Semana 06 — Rúbrica de Evaluación

## 📊 Distribución de Evidencias

| Tipo | Porcentaje | Actividad |
|------|-----------|-----------|
| 🧠 Conocimiento | 30% | Cuestionario teórico |
| 💪 Desempeño | 40% | Prácticas guiadas |
| 📦 Producto | 30% | Proyecto Blog API |

---

## 🧠 Conocimiento (30 puntos)

### Cuestionario — Relaciones JPA y Transacciones

| # | Pregunta | Puntos |
|---|----------|--------|
| 1 | ¿Qué diferencia hay entre `@OneToMany(fetch = LAZY)` y `EAGER`? | 5 |
| 2 | ¿Qué es el problema N+1 y cómo se resuelve con `JOIN FETCH`? | 5 |
| 3 | ¿Para qué sirve `mappedBy` en una relación bidireccional? | 4 |
| 4 | ¿Qué hace `CascadeType.ALL` vs `orphanRemoval = true`? | 4 |
| 5 | ¿Cuándo hace rollback `@Transactional`? ¿Y para checked exceptions? | 4 |
| 6 | ¿Cuál es la diferencia entre `Propagation.REQUIRED` y `REQUIRES_NEW`? | 4 |
| 7 | ¿Qué campo se necesita para bloqueo optimista? ¿Qué excepción lanza? | 4 |

**Mínimo aprobatorio: 21/30 (70%)**

---

## 💪 Desempeño (40 puntos)

### Práctica 01 — Relaciones OneToMany y ManyToOne (20 pts)

| Criterio | Puntos |
|----------|--------|
| `@OneToMany` + `@ManyToOne` correctamente mapeados (STEP 2) | 5 |
| `@OneToMany` con `cascade=ALL, orphanRemoval=true` (STEP 3) | 4 |
| Método helper `addComment()` mantiene bidireccionalidad (STEP 4) | 5 |
| Query `JOIN FETCH` elimina N+1 — visible en logs SQL (STEP 5) | 6 |

### Práctica 02 — @Transactional y Auditoría (20 pts)

| Criterio | Puntos |
|----------|--------|
| Sin `@Transactional`: datos parciales se guardan ante error (observación) | 4 |
| Con `@Transactional`: rollback completo — ningún dato persiste (STEP 2) | 6 |
| `@Transactional(readOnly = true)` a nivel de clase + overrides (STEP 3) | 4 |
| `@EnableJpaAuditing` + campos `createdAt`/`updatedAt` auto-poblados (STEP 4) | 6 |

**Mínimo aprobatorio: 28/40 (70%)**

---

## 📦 Producto (30 puntos)

### API de Blog con Relaciones

#### Funcionalidad de Endpoints (18 pts)

| Endpoint | Criterio | Puntos |
|----------|----------|--------|
| `GET /api/posts` | Retorna solo posts publicados con paginación | 3 |
| `GET /api/posts/{id}` | Post completo con author y comments (JOIN FETCH) | 3 |
| `POST /api/posts` | Crea post con author válido, retorna 201 | 3 |
| `POST /api/posts/{id}/comments` | Agrega comment con `addComment()`, retorna 201 | 3 |
| `PUT /api/posts/{id}/publish` | Publica post (published=true) | 3 |
| `DELETE /api/posts/{id}` | Elimina post + comments en cascade, retorna 204 | 3 |

#### JPA y Transacciones (8 pts)

| Criterio | Puntos |
|----------|--------|
| `PostRepository` con 3 queries implementadas (findByPublishedTrue, JOIN FETCH) | 4 |
| `PostService` con `@Transactional(readOnly = true)` + overrides correctos | 4 |

#### Calidad del Código (4 pts)

| Criterio | Puntos |
|----------|--------|
| Tests del repositorio pasan (`PostRepositoryTest`) | 2 |
| `createdAt` / `updatedAt` presentes en respuestas JSON | 2 |

**Mínimo aprobatorio: 21/30 (70%)**

---

## ✅ Criterios de Aprobación Global

| Condición | Requerimiento |
|-----------|--------------|
| Mínimo por tipo | ≥ 70% en cada evidencia |
| Tests | `PostRepositoryTest` — todos pasan |
| Endpoints | Al menos 5 de 6 funcionando |
| Auditoría | `createdAt` auto-asignado verificable en H2 Console |

---

## 📋 Entrega

- Repositorio GitHub con el proyecto en `3-proyecto/starter/`
- `README.md` del proyecto con instrucciones de ejecución
- Evidencia de tests pasando (screenshot o log)
