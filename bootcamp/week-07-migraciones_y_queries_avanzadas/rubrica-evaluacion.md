# Rúbrica de Evaluación — Semana 07
## Migraciones y Queries Avanzadas

---

## 📊 Distribución de Evidencias

| Tipo | Porcentaje | Descripción |
|------|-----------|-------------|
| 🧠 Conocimiento | 30% | Evaluación teórica de conceptos |
| 💪 Desempeño | 40% | Ejercicios prácticos en clase |
| 📦 Producto | 30% | Proyecto entregable funcional |

---

## 🧠 Conocimiento (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Flyway** | Explica convención `V1__desc.sql`, `flyway_schema_history`, cuándo usar `baseline`, irreversibilidad | Crea migraciones y sabe que son secuenciales | Confunde Flyway con `ddl-auto` |
| **Interface Projections** | Crea interfaz con solo los campos necesarios; explica ventaja vs cargar entidad completa | Projection básica con 2-3 campos | No entiende qué es una projection |
| **Specifications** | Crea `Specification<T>` composable; usa `and()`, `or()`; `JpaSpecificationExecutor` | Specification simple con 1 criterio | No sabe qué es `JpaSpecificationExecutor` |
| **Auditoría JPA** | Configura `@EnableJpaAuditing`, `@EntityListeners`, `@CreatedDate`, `@LastModifiedDate` | `@CreatedDate` y `@UpdatedDate` funcionando | Sin auditoría implementada |
| **`Pageable` avanzado** | Combina filtros via Specification + `Pageable`; `Page<T>` retorna `totalElements`, `totalPages` | Paginación + sort funcionando | Solo `findAll()` sin paginar |

---

## 💪 Desempeño (40%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Migración ejecutada** | `flyway_schema_history` tiene registros; `V1__` y `V2__` aplicados | Al menos `V1__` aplicado correctamente | `spring.flyway.enabled=false` o sin migraciones |
| **Projection en uso** | Endpoint retorna solo los campos de la projection (no la entidad completa) | Projection definida y usada básicamente | Retorna entidad completa siempre |
| **Specification activa** | `GET /resources?filter1=x&filter2=y` filtra dinámicamente con Specification | Un filtro optional funcionando | Sin filtros dinámicos |
| **Auditoría automática** | `created_at` y `updated_at` se llenan solos sin código manual | Solo `created_at` automático | Campos de auditoría con `LocalDateTime.now()` manual |

---

## 📦 Producto (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Schema versionado** | Toda la DDL en scripts Flyway; `ddl-auto: validate` en todos los entornos | `ddl-auto: validate` + Flyway funcionando | `ddl-auto: create` o `update` |
| **Búsqueda dinámica** | ≥3 filtros opcionales via Specification combinables entre sí | 2 filtros opcionales | Solo búsqueda exacta hardcodeada |
| **Campos auditados** | `createdAt` y `updatedAt` en todas las entidades; nunca nulos | `createdAt` en principal entidad | Sin auditoría |
| **Paginación completa** | `page`, `size`, `sort` funcionan; response incluye metadata de paginación | `page` y `size` funcionan | Sin paginación |

---

## 📏 Escala de Calificación

| Nota | Rango |
|------|-------|
| A | 90–100% |
| B | 80–89% |
| C | 70–79% |
| D | <70% |

---

## ✅ Criterios de Aprobación

- Mínimo **70%** en cada tipo de evidencia
- Sin `ddl-auto: create` o `update` — solo `validate` o `none`
- `flyway_schema_history` con al menos 2 registros
- Endpoint de búsqueda funciona con combinaciones de filtros opcionales
- Entrega puntual (penalización del 10% por día de retraso)
