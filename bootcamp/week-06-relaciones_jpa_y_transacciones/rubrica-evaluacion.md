# Rúbrica de Evaluación — Semana 06
## Relaciones JPA y Transacciones

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
| **`@OneToMany` / `@ManyToOne`** | Explica lado dueño (`@JoinColumn`), `mappedBy`, bidireccionalidad, `cascade` | Crea relación unidireccional correctamente | Confunde qué entidad tiene `@JoinColumn` |
| **`@ManyToMany`** | Usa `@JoinTable`, tabla intermedia personalizada con atributos extra | `@ManyToMany` simple funcionando | No sabe modelar relación muchos a muchos |
| **`FetchType`** | Explica LAZY vs EAGER con consecuencias; recomienda LAZY + `@EntityGraph` | Sabe que LAZY es más eficiente | `FetchType.EAGER` en todas las relaciones |
| **Problema N+1** | Identifica en logs de SQL el N+1; lo resuelve con `JOIN FETCH` o `@EntityGraph` | Identifica N+1, solución parcial | No detecta el N+1 en los logs |
| **`@Transactional`** | Explica propagación, cuándo va en service vs repository, rol de ACID | `@Transactional` en métodos de modificación | Sin `@Transactional` en operaciones de escritura |

---

## 💪 Desempeño (40%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Relación bidireccional** | Ambos lados correctamente configurados; helper method para mantener consistencia | Relación bidireccional funcionando | Solo unidireccional |
| **Tabla intermedia** | `@JoinTable` con nombre y columnas explícitas | `@ManyToMany` sin configuración extra | Sin `@JoinTable` |
| **Sin N+1** | Logs muestran 1-2 queries para cargar entidad + relaciones | Solución parcial (reduce N+1 a N/2) | N queries para N entidades registradas |
| **Sin `LazyInitializationException`** | Cero excepciones de lazy loading en cualquier endpoint | Solo en casos edge que no importan | `LazyInitializationException` frecuente |

---

## 📦 Producto (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Modelo de datos relacionado** | ≥2 entidades con al menos una relación bidireccional | 2 entidades con relación unidireccional | 1 entidad sin relaciones |
| **JPQL con JOIN** | Query con `JOIN FETCH` o `@EntityGraph` para cargar relaciones | Query JPQL simple funcionando | Carga todo con `findById` y lazy |
| **Integridad referencial** | Claves foráneas correctas en BD; `cascade = REMOVE` donde aplica | Claves foráneas sin cascade | Sin integridad referencial en BD |
| **`@Transactional` correcto** | Service methods de escritura todos marcados; `readOnly=true` en lectura | `@Transactional` en métodos de escritura | Sin transacciones |

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
- Sin `LazyInitializationException` en ningún endpoint
- Logs de SQL muestran ausencia de N+1 (`spring.jpa.show-sql=true` activo en dev)
- Repositorios no tienen `FetchType.EAGER` en relaciones sin justificación
- Entrega puntual (penalización del 10% por día de retraso)
